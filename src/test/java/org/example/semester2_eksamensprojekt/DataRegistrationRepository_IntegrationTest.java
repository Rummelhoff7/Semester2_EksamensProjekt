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


//Integration test = test med flere lag, Controller, Repository, database osv.
@SpringBootTest
public class DataRegistrationRepository_IntegrationTest {


    //Ingen Mocks

    @Autowired
    private DataRegistrationRepository dataRegistrationRepository;


    @Test
    @DisplayName("Get all leasings happy flow integration test")
    public void getAllLeasing_HappyFlow_Test() {
        // Assumptions
        int testCarId = 1;
        LocalDate startDate = LocalDate.now();
        dataRegistrationRepository.save(new Leasing(testCarId, startDate, startDate.plusMonths(5), 3500.0, true, "Test"));

        // Execution
        ArrayList<Leasing> leasings = dataRegistrationRepository.getAllLeasings();

        // Validation
        assertThat(leasings).isNotEmpty();

        boolean foundTestLeasing = false;
        for (Leasing l : leasings) {
            if (l.getCar_id() == testCarId) {
                foundTestLeasing = true;
                break;
            }
        }
        assertTrue(foundTestLeasing, "Should find the test leasing in the list");
    }

    @Test
    @DisplayName("Check if a leasing already exist for a car")
    public void LeasingExistsForCar_HappyFlow_Test() {
        // Assumptions
        int testCarId = 1;
        LocalDate startDate = LocalDate.now();
        Leasing leasingTest = new Leasing(testCarId, startDate, startDate.plusMonths(5), 3500.0, true, "Test");

        // Execution
        dataRegistrationRepository.save(leasingTest);

        // Validations
        assertTrue(dataRegistrationRepository.leasingExistsForCar(testCarId), "Leasing should exist for car");
        assertFalse(dataRegistrationRepository.leasingExistsForCar(1001230344), "Leasing should not exist for car");
    }





}
