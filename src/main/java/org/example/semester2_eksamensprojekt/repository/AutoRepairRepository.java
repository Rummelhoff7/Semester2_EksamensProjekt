package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// vi fortæller Spring at denne klasse er et repository
@Repository
public class AutoRepairRepository {

    //Spring injicerer en forbindelse til databasen
    @Autowired
    private DataSource dataSource;

// vi gemmer her en skadesrapport i databasen
    public void save(DamageReport damageReport) {
        String sql = "INSERT INTO damagereport(car_id, date) VALUES (?, ?)";

        // vi skaber forbindelse til databasen, indsætter variable, og udfører indsættelsen at variablerne
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getCar_id());
            statement.setDate(2, java.sql.Date.valueOf(damageReport.getDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// vi sletter en skadesrapport fra databasen
    public void delete(int car_id) {
        String sql = "DELETE FROM damagereport WHERE car_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, car_id);  // skriv car_id som parameter for at sige, hvilken skadesrapport der skal slettes
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// her tilføjer vi specifikke skader til skadesrapporten
    public void saveDamageItem(DamageItem damageItem) {
        String sql = "INSERT INTO damageitem (dmg_id, description, cost) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, damageItem.getDmg_id());
            statement.setString(2, damageItem.getDescription());
            statement.setDouble(3, damageItem.getCost());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
