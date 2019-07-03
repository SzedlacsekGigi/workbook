package com.workbook.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDao {

    private static final String DATABASE = "jdbc:postgresql://localhost/workbook";
    private static final String DB_USER = "david";
    private static final String DB_PASSWORD = "david";
    private static ConnectToDao instance = null;

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }



    /* A private Constructor prevents any other class from instantiating.
     */
    private ConnectToDao() {
    }

    public static ConnectToDao getInstance() {
        if (instance == null) {
            instance = new ConnectToDao();
        }
        return instance;
    }
}
