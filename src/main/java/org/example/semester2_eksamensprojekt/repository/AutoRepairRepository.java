package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class AutoRepairRepository {


    @Autowired
    private DataSource dataSource;

    public void save (DamageReport damageReport) {
        String sql = "INSERT INTO damagereport(car_id,date) VALUES ( ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getCar_id());
            statement.setDate(2,java.sql.Date.valueOf( damageReport.getDate()));

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}