package com.ecom.web.utility;

import com.ecom.web.model.Order;

public interface Facade {
  void process(Order order) throws Exception;
}