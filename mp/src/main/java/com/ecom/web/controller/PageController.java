package com.ecom.web.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.web.factory.GuitarFactory;
import com.ecom.web.factory.PedalFactory;
import com.ecom.web.model.Guitar;
import com.ecom.web.model.Order;
import com.ecom.web.model.Pedal;
import com.ecom.web.model.Product;
import com.ecom.web.repository.ProductRepository;
import com.ecom.web.utility.ConnectionPrototype;
import com.ecom.web.utility.DatabaseConnection;
import com.ecom.web.utility.DatabaseConnectionRegistry;
import com.ecom.web.utility.OrderProcessor;

@Controller
public class PageController implements ErrorController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderProcessor orderProcessor;

  @Autowired
  private GuitarFactory guitarFactory;

  @Autowired
  private PedalFactory pedalFactory;

  @GetMapping("/products")
  public String showProducts(Model model) {
    ConnectionPrototype dbPrototype = DatabaseConnectionRegistry.getConnection("default");
    Connection connection = ((DatabaseConnection) dbPrototype).getConnection();
    List<Product> products = new ArrayList<>();

    try {
      var statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

      while (resultSet.next()) {
        String productType = resultSet.getString("product_type");
        Product product = null;

        if ("Guitar".equalsIgnoreCase(productType)) {
          product = guitarFactory.createProduct(resultSet.getString("name"));
        } else if ("Pedal".equalsIgnoreCase(productType)) {
          product = pedalFactory.createProduct(resultSet.getString("name"));
        }

        if (product != null) {
          product.setId(resultSet.getLong("id"));
          product.setPrice(resultSet.getString("price"));
          product.setDescription(resultSet.getString("description"));
          product.setImageUrl(resultSet.getString("image_url"));
          products.add(product);
        }
      }
      model.addAttribute("product", products);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "product";
  }

  @GetMapping("/guitar/{name}")
  public String getGuitar(@PathVariable String name, Model model) {
    Order order = new Order();
    model.addAttribute("order", order);

    Product product = guitarFactory.createProduct(name);
    model.addAttribute("product", product);

    return "product";
  }

  @PostMapping("/guitar/{name}")
  public String postGuitar(
      @PathVariable String name,
      @ModelAttribute("order") Order order,
      Model model, RedirectAttributes redirectAttributes) {
    Product product = productRepository.findByName(name)
        .filter(p -> p instanceof Guitar)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    order.setProduct(product);

    try {
      orderProcessor.process(order);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      redirectAttributes.addFlashAttribute("error", e.getMessage());
      model.addAttribute("product", product);
      model.addAttribute("order", order);
      model.addAttribute("error", e.getMessage());
      return "product";
    }

    model.addAttribute("product", product);
    model.addAttribute("order", order);
    return "finalorder";
  }

  @GetMapping("/pedal/{name}")
  public String getPedal(@PathVariable String name, Model model) {
    Order order = new Order();
    model.addAttribute("order", order);

    Product product = pedalFactory.createProduct(name);
    model.addAttribute("product", product);

    return "product";
  }

  @PostMapping("/pedal/{name}")
  public String postPedal(
      @PathVariable String name,
      @ModelAttribute("order") Order order,
      Model model, RedirectAttributes redirectAttributes) {
    Product product = productRepository.findByName(name)
        .filter(p -> p instanceof Pedal)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    order.setProduct(product);

    try {
      orderProcessor.process(order);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      redirectAttributes.addFlashAttribute("error", e.getMessage());
      model.addAttribute("product", product);
      model.addAttribute("order", order);
      model.addAttribute("error", e.getMessage());
      return "product"; // Show form again with error
    }

    model.addAttribute("product", product);
    model.addAttribute("order", order);
    return "finalorder";
  }

  @GetMapping("/index")
  public String indexPage() {
    return "index";
  }

  @GetMapping("/thbb10")
  public String thbb10Page() {
    return "thbb10";
  }

  @GetMapping("/tod10")
  public String tod10Page() {
    return "tod10";
  }

  @GetMapping("/tod10n")
  public String tod10nPage() {
    return "tod10n";
  }

  @GetMapping("/guitars")
  public String guitarsPage() {
    return "guitars";
  }

  @GetMapping("/drumkits")
  public String drumkitsPage() {
    return "drumkits";
  }

  @GetMapping("/pedals")
  public String pedalsPage() {
    return "pedals";
  }

  @GetMapping("/ts9")
  public String ts9Page() {
    return "ts9";
  }

  @GetMapping("/dc2")
  public String dc2Page() {
    return "dc2";
  }

  @GetMapping("/looper")
  public String looperPage() {
    return "looper";
  }

  @GetMapping("/amplifiers")
  public String amplifiersPage() {
    return "amplifiers";
  }

  @GetMapping("/accessories")
  public String accessoriesPage() {
    return "accessories";
  }

  @GetMapping("/shipping")
  public String orderPage() {
    return "finalorder";
  }

  @GetMapping("/error")
  public String handleError() {
    return "error";
  }
}