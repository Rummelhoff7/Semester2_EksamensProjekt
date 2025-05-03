package org.example.semester2_eksamensprojekt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Autowired
    private DataSource dataSource;

    public String authenticateUser(String name, String password) {
        String sql = "SELECT role FROM user WHERE name = ? AND password = ?";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // ? placeholders bliver sat ind i statements
            statement.setString(1, name);
            statement.setString(2, password);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "none";
    }
}
