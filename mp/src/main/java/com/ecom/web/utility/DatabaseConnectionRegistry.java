package com.ecom.web.utility;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionRegistry {
  private static final Map<String, ConnectionPrototype> prototypes = new HashMap<>();

  static {
    prototypes.put("default", new DatabaseConnection(
        "jdbc:mysql://localhost:3306/ecommerceweb", "root", "password"));
  }

  public static ConnectionPrototype getConnection(String key) {
    return prototypes.get(key).clone();
  }

  public static void registerConnection(String key, ConnectionPrototype prototype) {
    prototypes.put(key, prototype);
  }
}
