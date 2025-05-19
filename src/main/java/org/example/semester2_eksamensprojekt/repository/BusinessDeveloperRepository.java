package org.example.semester2_eksamensprojekt.repository;

import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.model.ElectricCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BusinessDeveloperRepository{
    @Autowired
    private DataSource dataSource;

    public int rentedcars() {
        //Her tæller vi alle de biler som er blevet lejet ud
        String sql = "SELECT COUNT(*) AS total_leased_cars FROM cars WHERE status = 'Udlejet'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores attribute
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_leased_cars");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double totalamount(){
        //her ligger vi alle price sammen, for udlejet biler
        String sql = "SELECT SUM(price) AS total_amount FROM leasing WHERE status = 'Udlejet'";

        // Connect til database
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            //Her putter vi alle informationen fra databasen ind i vores attribute
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Denne metode bruges for Business Developer. Den returnerer en Arrayliste med biler, som er "Udlejet".
    // Denne metoder kalder en anden metode getCarFromResultSet(ResultSet resultSet). Metoden returnerer enten almindelige biler eller elbiler.
    public ArrayList<Car> getAllRentedCars(){
        ArrayList<Car> carList = new ArrayList<>();
        // Denne SQL-streng skal hente data fra to tabeller i databasen. Både electric_car og cars.
        // SELECT alle kolonner for begge tabeller.
        // LEFT JOIN bruges, da ikke alle biler er elbiler.
        // WHERE, Da vi kun vil finde biler der er UDLEJET.
        String sql = """
        SELECT c.*, ec.battery_capacity, ec.charging_time, ec.range_per_charge
        FROM cars c
        LEFT JOIN electric_car ec ON c.id = ec.id
        WHERE c.status = 'Udlejet'
        """;

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Går igennem alle biler i databasen.
            while (resultSet.next()) {
                // Tilføjer en bil til den tomme liste fra tidligere nævnt metode. Metoden kan ses nedenunder.
                carList.add(getCarFromResultSet(resultSet));
            }

            // Hvis der sker en databasefejl logges det.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Til sidst returnerer vi listen med både elbiler og almindelige biler som er UDLEJET.
        return carList;
    }

    // Denne metode bruges for Business Developer. Den returnerer en Arrayliste med biler, som er "Ledig".
    // Denne metoder kalder en anden metode getCarFromResultSet(ResultSet resultSet). Metoden returnerer enten almindelige biler eller elbiler.
    public ArrayList<Car> getAllAvailableCars() {
        ArrayList<Car> carList = new ArrayList<>(); //Tom liste.
        // Denne SQL-streng skal hente data fra to tabeller i databasen. Både electric_car og cars.
        // SELECT alle kolonner for begge tabeller.
        // LEFT JOIN bruges, da ikke alle biler er elbiler.
        // WHERE, Da vi kun vil finde biler der er ledige.
        String sql = """
        SELECT c.*, ec.battery_capacity, ec.charging_time, ec.range_per_charge  
        FROM cars c
        LEFT JOIN electric_car ec ON c.id = ec.id
        WHERE c.status = 'Ledig'
        """;

        // Her laver vi en ny Connection til databasen.
        // PreparedStatement hjælper med at stoppe SQL injections.
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // Går igennem alle biler i databasen.
            while (resultSet.next()) {
                // Tilføjer en bil til den tomme liste fra tidligere nævnt metode. Metoden kan ses nedenunder.
               carList.add(getCarFromResultSet(resultSet));
            }

        // Hvis der sker en databasefejl logges det.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Til sidst returnerer vi listen med både elbiler og almindelige biler som er LEDIGE
        return carList;
    }


    // Denne metode bliver kaldt fra getAllAvaiableCars() og getAllRentedCars().
    // Formålet med denne metode er, at undgå redundans i koden, da begge metoder skal bruge indholdet af denne metode.
    // Metoden returnerer enten en bil (superklasse) eller en elbil (subklasse) baseret på data i ResultSet.
    // Metoden kaster SQLException, hvis der skulle komme en database-relateret fejl.
    // OBS: Private metode. Det en "hjælpe" metode til getAllAvaiableCars() og getAllRentedCars(). Den skal ikke blive kaldt andre steder fra.
    private Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        // Dette er værdier både biler og elbiler har. Disse værdier er nødvendige for begge former køretøjer
        // Disse værdier kommer fra "cars" tabellen i vores sql-script.
        int id = resultSet.getInt("id");
        String framenumber = resultSet.getString("framenumber");
        String color = resultSet.getString("color");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        int equipmentLevel = resultSet.getInt("equipment_level");
        double steelPrice = resultSet.getDouble("steel_price");
        double registrationFee = resultSet.getDouble("registration_fee");
        double co2Emissions = resultSet.getDouble("co2_emissions");
        boolean limited = resultSet.getBoolean("limited");
        String status = resultSet.getString("status");
        String img = resultSet.getString("img");

        // Nu skal vi tage elbiler i betragtning. De har tre attributter som normale biler ikke har.
        // Hvis en bil ikke er i "electric_car" tabellen, LEFT JOIN vil give double værdien "NULL", da den ikke har en battery_capacity værdi.
        // Dvs. wasNull() metoden vil returnerer true, men hvis battery_capacity IKKE er null så returnerer false.
        double batteryCapacity = resultSet.getDouble("battery_capacity");

        // wasNull() returnerer true, hvis den sidste hentede værdi var NULL
        // isElectric bliver true, hvis battery_capacity IKKE er NULL (dvs. bilen er en elbil)
        boolean isElectric = !resultSet.wasNull(); //

        //isElectric = true, hvis elbil //isElectric = false, hvis bil
        if (isElectric) {
            //Hvis elbil, så henter vi resten af værdierne.
            double chargingTime = resultSet.getDouble("charging_time");
            double rangePerCharge = resultSet.getDouble("range_per_charge");

            // Returner et ElectricCar objekt med alle relevante data i konstruktøren.
            return new ElectricCar(id, framenumber, color, brand, model, equipmentLevel,
                    steelPrice, registrationFee, co2Emissions, limited, status, img,
                    batteryCapacity, chargingTime, rangePerCharge);

            // Hvis det ikke er en elbil, returner et almindeligt Car objekt.
        } else {
            return new Car(id, framenumber, color, brand, model, equipmentLevel,
                    steelPrice, registrationFee, co2Emissions, limited, status, img);
        }
    }
}
