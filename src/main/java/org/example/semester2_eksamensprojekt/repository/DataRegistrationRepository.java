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

    // Denne metode bliver kaldt fra DateRegistrationController, og den skal bruges til at GEMME en ny leasing i databasen.
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

    // Denne metode bliver kaldt fra DateRegistrationController, og den skal bruges til SLETTE en leasing fra databasen
    public void delete(int id) {
        // SQL streng, der sletter en leasing hvor id matcher.
        String sql = "DELETE FROM leasing WHERE id = ?";

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter den givene værdi id ind parameterindex 1.
            statement.setInt(1, id);

            // Til sidst eksekverer vi SQL statement som ændrer dataen i databasen. Denne metode bruger vi til dataændringer (INSERT, UPDATE, DELETE)
            statement.executeUpdate();

            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Denne metode bliver kaldt fra DateRegistrationController, og den skal bruges til at OPDATERE en leasing i databasen.
    public void update (Leasing updatedLeasing) {
        // SQL-streng, der opdaterer alle de skrevne attributter fra leasing, som har dette id.
        String sql = "UPDATE leasing SET car_id, start_date = ?, end_date = ?, price = ?, status = ?, customer_info = ? WHERE id = ?";

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter vores VALUES ind i placeholderne ?
            statement.setInt(1, updatedLeasing.getCar_id());

            // Ligesom før, så har PreparedStatement klassen ikke en setLocalDate metode, så her konverterer LocalDate til java.sql.Date
            statement.setDate(2, java.sql.Date.valueOf(updatedLeasing.getStart_date()));
            statement.setDate(3, java.sql.Date.valueOf(updatedLeasing.getEnd_date()));

            //Sætter resten af VALUES ind, med getmetoder fra Leasing klassen i model-package.
            statement.setDouble(4, updatedLeasing.getPrice());
            statement.setBoolean(5, updatedLeasing.isStatus());
            statement.setString(6, updatedLeasing.getCustomer_info());
            statement.setInt(7, updatedLeasing.getId());

            // executeUpdate = Ændrer og opdaterer databasen. Denne metode bruger vi til dataændringer (INSERT, UPDATE, DELETE)
            statement.executeUpdate();

            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}






