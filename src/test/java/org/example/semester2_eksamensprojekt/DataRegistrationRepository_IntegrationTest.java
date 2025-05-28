package org.example.semester2_eksamensprojekt;

import org.example.semester2_eksamensprojekt.model.Leasing;
import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


// Integration test = test med flere lag, Controller, Repository, database osv.
// Disse tester samarbejdet mellem databasen og repository.
@SpringBootTest
public class DataRegistrationRepository_IntegrationTest {
    //Ingen Mocks
    @Autowired
    private DataRegistrationRepository dataRegistrationRepository;

    // Denne test tjekker getAllLeasing() fra DataRegRepository, som vi forventer et happy flow.
    @Test
    @DisplayName("Get all leasings happy flow integration test")
    public void getAllLeasing_HappyFlow_Test() {
        // Assumptions
            // Test data
        int testCarId = 1;
        LocalDate startDate = LocalDate.now();
            // Gemmer et Leasing objekt i databasen.
        dataRegistrationRepository.save(new Leasing(testCarId, startDate, startDate.plusMonths(5), 3500.0, true, "Test"));

        // Execution
            // Da vi har gemt et leasing objekt, ved vi at vi har data i databasen.
            // Så vi tester denne metode, om den returnerer listen af leasings som den skal.
        ArrayList<Leasing> leasings = dataRegistrationRepository.getAllLeasings();

        // Validation
            // Vi tjekker om den returneret listen ikke er tom.
        assertThat(leasings).isNotEmpty();

            // Derefter går vi igennem listen af leasings, og prøver at finde den oprettede leasing.
        boolean foundTestLeasing = false;
        for (Leasing l : leasings) {
            // Leasingen skal have samme id som den oprettede leasing.
            if (l.getCar_id() == testCarId) {
                foundTestLeasing = true;
                break;
            }
        }
            //Hvis fountTestLeasing er true, så har vi fundet den oprettede leasing.
            //Hvis false, vil testen fejle.
        assertTrue(foundTestLeasing, "Should find the test leasing in the list");
    }

    //Denne test tjekker om en leasing allerede eksisterer på en bil
    @Test
    @DisplayName("Check if a leasing already exist for a car")
    public void LeasingExistsForCar_HappyFlow_Test() {
        // Assumptions
            // Test data.
        int testCarId = 1;
        LocalDate startDate = LocalDate.now();
            // Opretter ny leasingobjekt leasingTest.
        Leasing leasingTest = new Leasing(testCarId, startDate, startDate.plusMonths(5), 3500.0, true, "Test");

        // Execution
            // Gemmer leasingtest i repository (integration)
        dataRegistrationRepository.save(leasingTest);

        // Validations
            // assertTrue = den oprettede leasingtest burde findes med dette testid.
        assertTrue(dataRegistrationRepository.leasingExistsForCar(testCarId), "Leasing should exist for car");
            // assertFalse = Der burde ikke være en leasing med dette test id, da det ikke findes i databasen.
        assertFalse(dataRegistrationRepository.leasingExistsForCar(1001230344), "Leasing should not exist for car");
    }
}
