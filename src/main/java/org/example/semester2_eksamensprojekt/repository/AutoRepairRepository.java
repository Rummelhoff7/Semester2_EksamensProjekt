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
        String sql = "INSERT INTO damagereport(, id, car_id,current_date) VALUES (?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getId());
            statement.setInt(2, damageReport.getCar_id());
            statement.setDate(3, damageReport.getDate());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}