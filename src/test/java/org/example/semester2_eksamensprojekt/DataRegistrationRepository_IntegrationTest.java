package org.example.semester2_eksamensprojekt;

import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataRegistrationRepository_IntegrationTest {

    @Autowired
    private DataRegistrationRepository dataRegistrationRepository;

    @Test
    @DisplayName("Get all leasings happy flow integration test")
    public void getAllLeasingHappyFlow() {

    }

}
