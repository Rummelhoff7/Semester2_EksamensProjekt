package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DataRegistrationRepository {

    @Autowired // Spring annatation, som giver dig tilgang til en DataSource.
    private DataSource dataSource;

    // Denne metode bliver kaldt fra DateRegistrationController, og den skal bruges til at gemme en ny leasing i databasen.
    public void save(Leasing leasing){
        // SQL string, der indsætter den nye leasings attributer i databasen.
        // OBS! vi indsætter ikke id'et, da det er auto-genereret.'
        String sql = "INSERT INTO leasing(car_id, start_date, end_date, price, status, customer_info) VALUES (?, ?, ?, ?, ?, ?)";


        // Her laver vi en ny Connection til databasen, og sender en SQL-query.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter vores VALUES ind i placeholderne ?
            statement.setInt(1,leasing.getCar_id());

            //PreparedStatement klassen har ikke en setLocalDate metode, så her konverterer LocalDate til java.sql.Date
            statement.setDate(2, java.sql.Date.valueOf(leasing.getStart_date()));
            statement.setDate(3, java.sql.Date.valueOf(leasing.getEnd_date()));

            statement.setDouble(4,leasing.getPrice());
            statement.setBoolean(5,leasing.isStatus());
            statement.setString(6,leasing.getCustomer_info());

            // Til sidst eksekverer vi SQL statement som ændrer dataen i databasen.
            statement.executeUpdate();


        } // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
