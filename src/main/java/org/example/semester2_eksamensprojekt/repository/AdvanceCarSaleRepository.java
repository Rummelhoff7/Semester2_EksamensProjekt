package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.AdvanceCarSale;
import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.model.CarSalesInfo;
import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AdvanceCarSaleRepository {

    @Autowired
    private DataSource dataSource;

    public void save (AdvanceCarSale advanceCarSale) {
        String sql = "INSERT INTO advance_car_sale(car_id,terms, exceeded_kilometers, collection_point) VALUES ( ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, advanceCarSale.getCar_id());
            statement.setString(2, advanceCarSale.getTerms());
            statement.setInt(3, advanceCarSale.getExceeded_kilometers());
            statement.setString(4,advanceCarSale.getCollection_point());
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveTotalPrice(AdvanceCarSale advanceCarSale) {
        String sql = "UPDATE advance_car_sale SET buying_price = ? WHERE car_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, advanceCarSale.getBuying_price());
            statement.setInt(2, advanceCarSale.getCar_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CarSalesInfo save_price(int car_id) {
        String sql = "SELECT " +
                        "c.id, " +
                        "c.steel_price, " +
                        "SUM(COALESCE(di.cost, 0)) AS total_damage_cost, " +
                        "acs.exceeded_kilometers, " +
                        "c.steel_price - SUM(COALESCE(di.cost, 0)) AS steel_price_minus_total_cost, " +
                        "acs.exceeded_kilometers * 2.95 AS exceeded_km_cost, " +
                        "(c.steel_price - SUM(COALESCE(di.cost, 0))) - (acs.exceeded_kilometers * 2.95) AS final_price " +
                        "FROM cars c " +
                        "    JOIN damagereport dr ON c.id = dr.car_id " +
                        "    JOIN advance_car_sale acs ON c.id = acs.car_id " +
                        "    LEFT JOIN damageitem di ON dr.id = di.dmg_id " +
                        "WHERE c.id = ? " +
                        "GROUP BY c.id, c.steel_price, acs.exceeded_kilometers;";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, car_id);  // Set the parameter value

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double finalPrice = resultSet.getDouble("final_price");
                double exceededKmCost = resultSet.getDouble("exceeded_km_cost");

                return new CarSalesInfo(finalPrice, exceededKmCost);
            }



        } catch (SQLException e){
            e.printStackTrace();
        }


        return null;
    }

    public int exceeded_kilometers(int car_id){
        String sql = "SELECT exceeded_kilometers FROM advance_car_sale WHERE id = ?";

        int exceeded_kilometers = 0;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, car_id);  // Set the parameter value

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exceeded_kilometers = resultSet.getInt("exceeded_kilometers");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return exceeded_kilometers;
    }

    public ArrayList<DamageItem> getAllDamageItemWithCarID(int car_id){
        ArrayList<DamageItem> DamageItemList = new ArrayList<>();
        //Her vælger vi alle de biler som har status Available
        String sql = "SELECT " +
                        "di.description, " +
                        "di.cost " +
                        "FROM cars c " +
                        "JOIN damagereport dr ON c.id = dr.car_id " +
                        "LEFT JOIN damageitem di ON dr.id = di.dmg_id " +
                        "WHERE c.id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, car_id);  // Set the parameter value
            //Her putter vi alle informationen fra databasen ind i vores model og derefter ind i vore Array
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DamageItem damageItem = new DamageItem();
                    damageItem.setDescription(resultSet.getString("description"));
                    damageItem.setCost(resultSet.getDouble("cost"));
                    DamageItemList.add(damageItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DamageItemList;
    }
}
