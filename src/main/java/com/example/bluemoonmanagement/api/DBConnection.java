package com.example.bluemoonmanagement.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/bluemoon_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

//    public static void main(String[] args) {
//        DBConnection connection1 = new DBConnection();
//        connection1.connectToDatabase();
//    }
}
