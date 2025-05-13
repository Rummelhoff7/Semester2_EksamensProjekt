package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.AdvanceCarSale;
import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


}
