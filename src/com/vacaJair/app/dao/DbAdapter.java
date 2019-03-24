package com.vacaJair.app.dao;

import java.sql.*;

public class DbAdapter {
    /*- Constants */
    String jdbUrl = "jdbc:postgresql://localhost:5432/AppUsers";
    String username = "postgres";
    String password = "Admin123";

    /* Database variables */
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    /* Constructor */

    public DbAdapter() {
    }

    /**
     * Connect to database
     */

    public void connect() {
        try {
            connection = DriverManager.getConnection(jdbUrl, username, password);
            System.out.println("Database connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to database
     */

    public void disconnect() {
        try {
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {

        }
    }

}
