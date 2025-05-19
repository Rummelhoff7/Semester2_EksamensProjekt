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

    @Autowired
    private DataSource dataSource;

    public void save(DamageReport damageReport) {
        String sql = "INSERT INTO damagereport(car_id, date) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getCar_id());
            statement.setDate(2, java.sql.Date.valueOf(damageReport.getDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM damagereport WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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

    public List<DamageReport> getAllDamageReports() {
        List<DamageReport> reports = new ArrayList<>();
        String sql = "SELECT * FROM damagereport";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
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

    public List<DamageItem> getDamageItemsByReportId(int dmg_id) {
        List<DamageItem> damageItems = new ArrayList<>();
        String sql = "SELECT * FROM damageitem WHERE dmg_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dmg_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String description = rs.getString("description");
                double cost = rs.getDouble("cost");
                damageItems.add(new DamageItem(dmg_id, description, cost));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return damageItems;
    }


    public DamageReport getDamageReportById(int id) {
        DamageReport damageReport = null;
        String sql = "SELECT * FROM damagereport WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    damageReport = new DamageReport();
                    damageReport.setId(rs.getInt("id"));
                    damageReport.setCar_id(rs.getInt("car_id"));
                    damageReport.setDate(rs.getDate("date").toLocalDate());
                    // evt. hent damageItems separat hvis du ønsker det her
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damageReport;
    }

}
