package com.ecom.web.factory;

import com.ecom.web.model.Product;

public interface AbstractFactory {
  Product createProduct(String name);
}
