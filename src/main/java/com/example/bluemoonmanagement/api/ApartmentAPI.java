package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Apartment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApartmentAPI {
    public static List<Apartment> getAllApartments() {
        String query = "SELECT * FROM Apartment";
        List<Apartment> apartments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Apartment apartment = new Apartment();
                apartment.setApartmentId(resultSet.getInt("apartmentId"));
                apartment.setOwnerId(resultSet.getInt("ownerId"));
                apartment.setArea(resultSet.getDouble("area"));
                apartment.setFloor(resultSet.getInt("floor"));
                apartment.setRoomNumber(resultSet.getString("roomNumber"));

                apartments.add(apartment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    public static void main(String[] args) {
        List<Apartment> apartments = getAllApartments();
        for (Apartment apartment : apartments) {
            System.out.println(apartment);
        }
    }
}
