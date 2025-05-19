package com.ecom.web.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements ConnectionPrototype {
  private String url;
  private String username;
  private String password;
  private Connection connection;

  public DatabaseConnection(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
    createConnection();
  }

  private void createConnection() {
    try {
      connection = DriverManager.getConnection(url, username, password);
      System.out.println("Database connected successfully.");

    } catch (SQLException e) {
      throw new RuntimeException("Failed to connect to the database.", e);
    }
  }

  public Connection getConnection() {
    return connection;
  }

  @Override
  public ConnectionPrototype clone() {
    System.out.println("Cloning database connection.");
    return new DatabaseConnection(url, username, password);
  }
}
