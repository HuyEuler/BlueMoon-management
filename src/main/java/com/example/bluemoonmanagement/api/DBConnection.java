package com.example.bluemoonmanagement.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection connection;
//    private static final String URL = "jdbc:mysql://localhost:3306/bluemoon2";
private static final String URL = "jdbc:mysql://localhost:3306/bluemoon_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        System.out.println("Connection established successfully");
        return connection;
    }
public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Connection established successfully");
        } catch (SQLException e) {
            System.out.println("Loi ket noi");
            e.printStackTrace();
        }
    }
}
