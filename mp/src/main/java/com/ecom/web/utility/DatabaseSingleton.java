package com.ecom.web.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
  private static DatabaseSingleton instance;
  private static Connection connection;

  private static String url;
  private static String user;
  private static String password;
  private static String jdbcDriver;

  private DatabaseSingleton() {
  }

  public static synchronized DatabaseSingleton getInstance() {
    if (instance == null) {
      instance = new DatabaseSingleton();
    }
    return instance;
  }

  private static Connection getDBConnection() {
    Connection connection = null;
    try {
      Class.forName(jdbcDriver);
      connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to the database", e);
    } catch (ClassNotFoundException cnfe) {
      throw new RuntimeException("Class not found!", cnfe);
    }
    return connection;
  }

  public static Connection getConnection() {
    return ((connection != null)
        ? connection
        : getDBConnection());
  }

  public static void testObjectProperties() {
    try {
      Connection x = DriverManager
          .getConnection(url, user, password);
      Connection y = DriverManager
          .getConnection(url, user, password);

      if (x.equals(y) && y.equals(x)) {
        System.out.println("Passed symmetric property!");
      } else {
        System.out.println("FAILED symmetric property!");
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
  }
}
