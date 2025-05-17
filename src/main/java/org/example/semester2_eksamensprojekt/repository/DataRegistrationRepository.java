package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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

            LocalDate endDate = calculateEndDate(leasing.getStart_date(), leasing.getEnd_date(), leasing.isStatus());

            //PreparedStatement klassen har ikke en setLocalDate metode, så her konverterer LocalDate til java.sql.Date
            statement.setDate(2, java.sql.Date.valueOf(leasing.getStart_date()));
            statement.setDate(3, java.sql.Date.valueOf(endDate));
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
        String sql = "UPDATE leasing SET car_id = ?, start_date = ?, end_date = ?, price = ?, status = ?, customer_info = ? WHERE id = ?";

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter vores VALUES ind i placeholderne ?
            statement.setInt(1, updatedLeasing.getCar_id());

            // Ligesom før, så har PreparedStatement klassen ikke en setLocalDate metode, så her konverterer LocalDate til java.sql.Date
            statement.setDate(2, java.sql.Date.valueOf(updatedLeasing.getStart_date()));

            LocalDate endDate = calculateEndDate(updatedLeasing.getStart_date(), updatedLeasing.getEnd_date(), updatedLeasing.isStatus());
            statement.setDate(3, java.sql.Date.valueOf(endDate));

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

    public ArrayList<Leasing> getAllLeasings() {
        ArrayList<Leasing> leasingList = new ArrayList<>();
        String sql = "SELECT * FROM leasing";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);

             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Leasing leasing = new Leasing();
                leasing.setId(resultSet.getInt("id"));
                leasing.setCar_id(resultSet.getInt("car_id"));
                leasing.setStart_date(resultSet.getDate("start_date").toLocalDate());
                leasing.setEnd_date(resultSet.getDate("end_date").toLocalDate());
                leasing.setPrice(resultSet.getDouble("price"));
                leasing.setStatus(resultSet.getBoolean("status"));
                leasing.setCustomer_info(resultSet.getString("customer_info"));
                leasingList.add(leasing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leasingList;
    }


    public Leasing getLeasingByID (int id) {
        Leasing leasing = new Leasing();
        // sql "id" strengen henvender sig til databasen. ? Er der hvor vi sætter id i metoden
        String sql = "SELECT * FROM leasing WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            // executeQuery = Forespørgsel til databasen om Leasing id
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    leasing.setId(resultSet.getInt("id"));
                    leasing.setCar_id(resultSet.getInt ("car_id"));
                    leasing.setStart_date(resultSet.getDate("start_date").toLocalDate());
                    leasing.setEnd_date(resultSet.getDate("end_date").toLocalDate());
                    leasing.setPrice(resultSet.getDouble("price"));
                    leasing.setStatus(resultSet.getBoolean("status"));
                    leasing.setCustomer_info(resultSet.getString("customer_info"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leasing;
    }

    public LocalDate calculateEndDate(LocalDate startDate, LocalDate givenEndDate, boolean status) throws IllegalArgumentException {
        if (status) {
            // Limited subscription: fixed 5 months from startDate
            return startDate.plusMonths(5);
        } else {
            // Unlimited subscription: given endDate if valid, else min 3 months
            LocalDate minEndDate = startDate.plusMonths(3);
            if (givenEndDate == null || givenEndDate.isBefore(minEndDate)) {
                throw new IllegalArgumentException();
            }
            return givenEndDate;
        }
    }

    public boolean leasingExistsForCar(int car_id) {
        String sql = "SELECT COUNT(*) FROM leasing WHERE car_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,car_id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean carExists(int car_id) {
        String sql = "SELECT COUNT(*) FROM cars WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,car_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}






