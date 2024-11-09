//package com.example.bluemoonmanagement.api;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class TestDBConnection {
//    public static void main(String[] args) {
//        var url = "jdbc:mysql://localhost:3306/mydatabase";
//        var user = "root";
//        var password = "";
//        try(Connection conn = DriverManager.getConnection(url, user, password)) {
//            System.out.println(conn.getCatalog());
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}

package com.example.bluemoonmanagement.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBConnection {
    public static void main(String[] args) {
        var url = "jdbc:mysql://localhost:3306/mydatabase";
        var user = "root";
        var password = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database: " + conn.getCatalog());

            // Tạo câu lệnh SQL để truy vấn tất cả từ bảng tbl_student
            String query = "SELECT * FROM tbl_student";

            // Tạo đối tượng Statement để thực hiện câu lệnh SQL
            try (Statement stmt = conn.createStatement()) {
                // Thực hiện câu lệnh SQL và lấy kết quả
                ResultSet rs = stmt.executeQuery(query);

                // Duyệt qua kết quả và in ra các cột của mỗi bản ghi
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String mail = rs.getString("email");  // Lấy giá trị của cột mail
                    float avgScore = rs.getFloat("avg_grade");  // Lấy giá trị của cột avg_score
                    System.out.println("ID: " + id + ", Name: " + name + ", Mail: " + mail + ", Avg Score: " + avgScore);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}