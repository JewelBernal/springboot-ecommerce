package com.ecom.web.utility;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecom.web.model.Order;
import com.ecom.web.model.Product;
import com.ecom.web.repository.OrderRepository;
import com.ecom.web.repository.ProductRepository;

@Component
public class OrderProcessor implements Facade, Serializable {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private EmailService emailService;

  @Override
  public void process(Order order) {

    if (!CardValidator.isValidCreditCard(order.getCreditCard())) {
      throw new IllegalArgumentException("Invalid credit card number. >:(");
    }

    Product dbProduct = productRepository.findById(order.getProduct().getId())
        .orElseThrow(() -> new IllegalArgumentException("Product not found."));

    if (dbProduct.getQuantity() < order.getQuantity()) {
      throw new IllegalArgumentException("Product is out of stock. :(");
    }

    dbProduct.setQuantity(dbProduct.getQuantity() - order.getQuantity());
    productRepository.save(dbProduct);

    order.setProduct(dbProduct);
    order.setTotalPrice(dbProduct.getPrice());
    order.setStatus("PAID");
    orderRepository.save(order);

    emailService.sendEmail(
        order.getEmail(),
        "NO-REPLY: OneStore Order Receipt",
        "Thank you for purchasing " + dbProduct.getName() + ", " + order.getName() + "!" +
            "\nThis serves as your official digital receipt." +
            "\n======================================" +
            "\nORDER ID: " + dbProduct.getId() + "-" + order.getId() +
            "\nQuantity: " + order.getQuantity() +
            " item/s \nTotal: ₱" + order.getTotalPrice() +
            "\nExpect your order to arrive within 2-3 weeks!" +
            "\n=======================================" +
            "\n" +
            "\nFor assistance, email us at support@onestore.com" +
            "\nOneStore, best place for your music needs.");
  }
}
