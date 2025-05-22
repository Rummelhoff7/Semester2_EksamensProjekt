package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutoRepairRepository {

    // Datasource bliver automatisk koblet på via Spring – bruges til at få DB-forbindelse
    @Autowired
    private DataSource dataSource;


    //Gemmer en ny skadesrapport i databasen og returnerer det ID, databasen har givet den.

    public int save(DamageReport damageReport) {
        String sql = "INSERT INTO damagereport(car_id, date) VALUES (?, ?)";
        int generatedId = -1;

        try (
                // Opretter forbindelse til databasen
                Connection connection = dataSource.getConnection();
                // Forbereder SQL og gør klar til at hente det automatisk genererede ID
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            // Sætter car_id og dato som parametre i SQL'en
            statement.setInt(1, damageReport.getCar_id());
            statement.setDate(2, java.sql.Date.valueOf(damageReport.getDate()));

            // Kører SQL'en
            statement.executeUpdate();

            // Henter det ID som databasen lavede til den nye række
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Logger fejl hvis noget går galt
        }

        return generatedId;
    }

    // Sletter en skadesrapport ud fra dens ID
    public void delete(int id) {
        String sql = "DELETE FROM damagereport WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // Sætter ID som parameter
            statement.executeUpdate(); // Kører sletningen
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sletter alle skader der hører til en skadesrapport
    public void deleteDamageItem(int id) {
        String sql = "DELETE FROM damageitem WHERE dmg_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Gemmer en enkelt skade i databasen
    public void saveDamageItem(DamageItem damageItem) {
        String sql = "INSERT INTO damageitem (dmg_id, description, cost) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter alle tre værdier: reference til rapport, beskrivelse og pris
            statement.setInt(1, damageItem.getDmg_id());
            statement.setString(2, damageItem.getDescription());
            statement.setDouble(3, damageItem.getCost());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Henter alle skadesrapporter fra databasen
    public List<DamageReport> getAllDamageReports() {
        List<DamageReport> reports = new ArrayList<>();
        String sql = "SELECT * FROM damagereport";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Læser hver række og laver et DamageReport-objekt
                int id = rs.getInt("id");
                int car_id = rs.getInt("car_id");
                LocalDate date = rs.getDate("date").toLocalDate();

                reports.add(new DamageReport(id, car_id, date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    // Henter alle skader der hører til en bestemt rapport
    public List<DamageItem> getDamageItemsByReportId(int dmg_id) {
        List<DamageItem> damageItems = new ArrayList<>();
        String sql = "SELECT * FROM damageitem WHERE dmg_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dmg_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Laver et objekt for hvert skade
                String description = rs.getString("description");
                double cost = rs.getDouble("cost");
                damageItems.add(new DamageItem(dmg_id, description, cost));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return damageItems;
    }

    // Henter én enkelt skadesrapport baseret på ID
    public DamageReport getDamageReportById(int id) {
        DamageReport damageReport = null;
        String sql = "SELECT * FROM damagereport WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Hvis den finder rapporten, bygges et DamageReport-objekt
                    damageReport = new DamageReport();
                    damageReport.setId(rs.getInt("id"));
                    damageReport.setCar_id(rs.getInt("car_id"));
                    damageReport.setDate(rs.getDate("date").toLocalDate());


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damageReport;
    }

    // Opdaterer en eksisterende skadesrapport med nye værdier
    public void updateDamageReport(DamageReport damageReport) {
        String sql = "UPDATE damagereport SET car_id = ?, date = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter de nye værdier i SQL'en
            statement.setInt(1, damageReport.getCar_id());
            statement.setDate(2, Date.valueOf(damageReport.getDate()));
            statement.setInt(3, damageReport.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
