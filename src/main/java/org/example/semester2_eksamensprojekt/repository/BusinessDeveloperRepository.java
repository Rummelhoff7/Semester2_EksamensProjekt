package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository

public class BusinessDeveloperRepository {
    @Autowired
    private DataSource dataSource;

    public int rentedcars() {
        //Her tæller vi alle de biler som er blevet lejet ud
        String sql = "SELECT COUNT(*) AS total_leased_cars FROM cars WHERE status = 'Leased'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores attribute
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_leased_cars");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double totalamount(){
        //her ligger vi alle price sammen, for udlejet biler
        String sql = "SELECT SUM(price) AS total_amount FROM leasing WHERE status = 'Leased'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores attribute
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Car> getAllRentedCars(){
        ArrayList<Car> carList = new ArrayList<>();
        //Her vælger vi alle biler som har status leased.
        String sql = "SELECT * FROM cars WHERE status = 'Leased'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores model og derefter ind i vore Array
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setFramenumber(resultSet.getString("framenumber"));
                    car.setColor(resultSet.getString("color"));
                    car.setBrand(resultSet.getString("brand"));
                    car.setModel(resultSet.getString("model"));
                    car.setEquipment_level(resultSet.getInt("equipment_level"));
                    car.setSteel_price(resultSet.getDouble("steel_price"));
                    car.setRegistration_fee(resultSet.getDouble("registration_fee"));
                    car.setCO2_emissions(resultSet.getDouble("co2_emissions"));
                    car.setLimited(resultSet.getBoolean("limited"));
                    car.setStatus(resultSet.getString("status"));
                    car.setImg(resultSet.getString("img"));

                    carList.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carList;
    }

    public ArrayList<Car> getAllAvailableCars(){
        ArrayList<Car> carList = new ArrayList<>();
        //Her vælger vi alle de biler som har status Available
        String sql = "SELECT * FROM cars WHERE status = 'Available'";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores model og derefter ind i vore Array
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setFramenumber(resultSet.getString("framenumber"));
                    car.setColor(resultSet.getString("color"));
                    car.setBrand(resultSet.getString("brand"));
                    car.setModel(resultSet.getString("model"));
                    car.setEquipment_level(resultSet.getInt("equipment_level"));
                    car.setSteel_price(resultSet.getDouble("steel_price"));
                    car.setRegistration_fee(resultSet.getDouble("registration_fee"));
                    car.setCO2_emissions(resultSet.getDouble("co2_emissions"));
                    car.setLimited(resultSet.getBoolean("limited"));
                    car.setStatus(resultSet.getString("status"));
                    car.setImg(resultSet.getString("img"));

                    carList.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carList;
    }

}
