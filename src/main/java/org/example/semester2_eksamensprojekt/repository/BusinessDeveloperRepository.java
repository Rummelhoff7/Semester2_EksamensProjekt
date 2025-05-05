package org.example.semester2_eksamensprojekt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class BusinessDeveloperRepository {
    @Autowired
    private DataSource dataSource;

    public int rentedcars() {
        //her finder den role ud fra name og password
        String sql = "SELECT COUNT(*) AS total_leased_cars FROM cars WHERE status = 'Leased'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


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
        //her finder den role ud fra name og password
        String sql = "SELECT COUNT(*) AS total_amount FROM cars WHERE status = 'Leased'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total_leased_cars");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
