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
public class CarRepository {

    @Autowired // Spring annatation, som giver dig tilgang til en DataSource.
    private DataSource dataSource;

    public ArrayList<Car> getAllLimitedLeasing() {
        ArrayList<Car> carList = new ArrayList<>();
        //Her tæller vi alle de biler som er blevet lejet ud
        String sql ="SELECT cars.* "+
                "FROM leasing "+
                "JOIN cars ON leasing.car_id = cars.id " +
                "WHERE DATEDIFF(leasing.end_date, leasing.start_date) >= 153;";

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
