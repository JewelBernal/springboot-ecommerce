package com.ecom.web.factory;

import com.ecom.web.model.Product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecom.web.repository.ProductRepository;

@Component
public class PedalFactory implements AbstractFactory {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product createProduct(String name) {
    Optional<Product> product = productRepository.findByName(name);
    return product.orElseThrow(() -> new IllegalArgumentException("Pedal not found: " + name));
  }
}