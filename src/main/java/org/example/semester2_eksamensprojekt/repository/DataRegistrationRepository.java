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

    @Autowired // Spring annotation, som giver dig tilgang til en DataSource.
    private DataSource dataSource;

    //Mads
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

    //Mads
    // Denne metode bliver kaldt fra DateRegistrationController, og den skal bruges til SLETTE en leasing fra databasen
    public void delete(int id) {
        // SQL streng, der sletter en leasing hvor id matcher.
        String sql = "DELETE FROM leasing WHERE id = ?";

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter den givne værdi id ind parameterindex 1.
            statement.setInt(1, id);

            // Til sidst eksekverer vi SQL statement som ændrer dataen i databasen. Denne metode bruger vi til dataændringer (INSERT, UPDATE, DELETE)
            statement.executeUpdate();

            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Mads
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

            // Hvis der skulle komme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Mads
    // Denne metode returnerer en Arraylist med alle Leasing objekter i databasen.
    public ArrayList<Leasing> getAllLeasings() {
        //Opretter tom Leasing arraylist, hvor data fra SQL-query skal indsættes.
        ArrayList<Leasing> leasingList = new ArrayList<>();
        // SQL-script, der tager alle kolonner fra leasing tabellen.
        String sql = "SELECT * FROM leasing";

        // Connecter til databasen.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);

             ResultSet resultSet = statement.executeQuery()) {

            // While løkke, der går igennem alt i vores resultset. Vi bruger getters og setter til alle parametre.
            while (resultSet.next()) {
                // Opretter nyt leasing objekt og sætter dens tilføjer alle dens attributter.
                Leasing leasing = new Leasing();
                leasing.setId(resultSet.getInt("id"));
                leasing.setCar_id(resultSet.getInt("car_id"));
                leasing.setStart_date(resultSet.getDate("start_date").toLocalDate());
                leasing.setEnd_date(resultSet.getDate("end_date").toLocalDate());
                leasing.setPrice(resultSet.getDouble("price"));
                leasing.setStatus(resultSet.getBoolean("status"));
                leasing.setCustomer_info(resultSet.getString("customer_info"));
                // Tilføjer leasing objekt til arraylist.
                leasingList.add(leasing);
            }
            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Returnerer hele listen med leasings.
        return leasingList;
    }


    //Mads
    // Denne metode returnerer en leasing med givent id.
    public Leasing getLeasingByID (int id) {
        Leasing leasing = new Leasing();
        // sql "id" strengen henvender sig til databasen. ? Er der hvor vi sætter id i metoden
        String sql = "SELECT * FROM leasing WHERE id = ?";

        // Connecter til database.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter den givne værdi id ind parameterindex 1.
            statement.setInt(1, id);

            // executeQuery = Forespørgsel til databasen om Leasing id
            try (ResultSet resultSet = statement.executeQuery()) {
                // Hvis resultSet finder Leasing med id'et, så sætter vi leasing data fra databasen ind.
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
            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Returnerer leasingen
        return leasing;
    }

    //Mads
    // Metode der regner slutdato ud ift. bilens abonnement. Bliver brugt til fejlhåndtering i create og update leasing i DataReg. Controller.
    public LocalDate calculateEndDate(LocalDate startDate, LocalDate givenEndDate, boolean status) throws IllegalArgumentException {
        if (status) {
            // Limited abonnement: Fast på 5 måneder fra startDate
            return startDate.plusMonths(5);
        } else {
            // Unlimited abonnement: hvis status er unlimited, min 3 måneder.
            LocalDate minEndDate = startDate.plusMonths(3);
            // Hvis der er fejl i dato eller den givne LocalDate er tom.
            if (givenEndDate == null || givenEndDate.isBefore(minEndDate)) {
                throw new IllegalArgumentException("Datoen skal minimum være på 3 måneder fra startdatoen. Datoen er: " + givenEndDate);
            }
            // Returnerer slutdato ift. abonnement.
            return givenEndDate;
        }
    }

    //Mads
    // Denne metode tjekker om der allerede findes en leasing på en bil. Bliver brugt til fejlhåndtering i create leasing i DataReg. Controller.
    // Returnere true, hvis bil eksisterer. False hvis ikke.
    public boolean leasingExistsForCar(int car_id) {
        // SQL streng, går igennem leasing tabellen og tæller hvor mange gange det givne car_id optræder.
        String sql = "SELECT COUNT(*) FROM leasing WHERE car_id = ?";

        // Connecter til database.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter den givne værdi id ind parameterindex 1.
            statement.setInt(1,car_id);

            //Kører query med resultatet, som indeholder et tal.
            ResultSet resultSet = statement.executeQuery();
            // Hvis det givne tal er større end 0, så betyder det, at der allerede er en leasing på bilen.
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Hvis der ikke bliver fundet resultat, så returnerer vi bare false, så der ikke er en leasing på bilen.
        return false;
    }

    //Mads
    // Denne metoder tjekker om bilen eksisterer i databasen. Bliver brugt til fejlhåndtering i create og update leasing i DataReg. Controller.
    public boolean carExists(int car_id) {
        // SQL streng, som går igennem cars tabellen og tæller hvor mange gange det givne id optræder.
        String sql = "SELECT COUNT(*) FROM cars WHERE id = ?";

        //Connecter til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter den givne værdi id ind parameterindex 1.
            statement.setInt(1,car_id);

            //Kører query med resultatet, som indeholder et tal.
            ResultSet resultSet = statement.executeQuery();

            //hvis car objekt bliver fundet.
            if (resultSet.next()){
                // count = bilens id i kolonnenindex 1.
                int count = resultSet.getInt(1);
                // Hvis count er større end 0 (hvis count indeholder en værdi), så returner den true. Bil fundet.
                return count > 0;
            }
            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //Bil blev ikke fundet, returnerer false.
        return false;
    }

    //Mads
    // Denne metode tjekker om leasing eksisterer på bil bortset fra bil en som skal opdateres.
    // Dette er nødvendigt. Da det ville skabe fejl ift. opdatering af bil, da den altid vil have en leasing.
    // Bliver brugt til fejlhåndtering i create og update leasing i DataReg. Controller.
    public boolean leasingExistsForCarExcludingId(int car_id, int excludeLeasingId) {
        String sql = "SELECT COUNT(*) FROM leasing WHERE car_id = ? AND id <> ?";

        // Connecter til databasen
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Sætter parameter index 1 og 2, ind i placeholder (?,?)
            statement.setInt(1, car_id);
            statement.setInt(2, excludeLeasingId);

            ResultSet resultSet = statement.executeQuery();
            //
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            // Hvis der skulle komnme en fejl i databasen, sender den catch-block en fejl tilbage.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Hvis der ikke bliver fundet resultat, så returnerer vi bare false, så der ikke er en leasing på bilen.
        return false;
    }
}






