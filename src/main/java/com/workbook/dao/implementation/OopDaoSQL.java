package com.workbook.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OopDaoSQL implements com.workbook.dao.OopDao {


    private static com.workbook.dao.implementation.OopDaoSQL instance = null;

    private OopDaoSQL() {
    }

    public static com.workbook.dao.implementation.OopDaoSQL getInstance() {
        if (instance == null) {
            instance = new com.workbook.dao.implementation.OopDaoSQL();
        }
        return instance;
    }





    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        )
        {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost/workbook",
                "david",
                "david");
    }

    @Override
    public List<String> getAll() {
        List<String> questions = new ArrayList<String>();
        String query = "select * from workbook.new_schema.oop;";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            while (resultSet.next()) {
                questions.add(resultSet.getString("questions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
