package org.example.semester2_eksamensprojekt;


import org.example.semester2_eksamensprojekt.controller.DataRegistrationController;
import org.example.semester2_eksamensprojekt.model.Leasing;
import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataRegistrationController_UnitTest {

    @Mock //SQL- strengen vil ikke integreres i unit test
    // Vi mocker repositorien.
    private DataRegistrationRepository dataRegistrationRepository;

    @Mock
    private Model model;

    @InjectMocks // Isolerer controlleren.
    private DataRegistrationController dataRegistrationController;

    @Test
    @DisplayName("deleteLeasing Happy flow test")
    public void deleteLeasingHappyFlow_Test(){
        //Assumptions
        //Test data
        int leasingId = 1;
        //Execution
        //Kalder den metode som skal testes.
        String result = dataRegistrationController.deleteLeasing(leasingId);
        // Validation
        // Tjekker om controlleren har kalt metoden "delete()" i repositorien.
        // Da repositorien er mocked, vil ingen "rigtig" leasing blive slettet.
        verify(dataRegistrationRepository).delete(leasingId);
        // "Asserter" metoden returnerer den rigtige URL, som stemmer overens med controlleren.
        assertEquals("redirect:/dataRegistrationAllLeasings?user_role=data_registration", result);
    }

    @Test
    @DisplayName("deleteLeasing Exception flow test")
    public void deleteLeasingExceptionFlow_Test(){
        // Assumptions
        //Test data
        int leasingId = 1;
        // Vi kaster en exception i metoden "delete()" i mockobjektet(Mockito, når den skal slette testede leasing. (ExceptionFlow)
        // Simulerer exception flow i metoden (Database error).
        doThrow(new RuntimeException("Database error")).when(dataRegistrationRepository).delete(leasingId);

        // Execution
        //Kalder den metode som skal testes.
        String result = dataRegistrationController.deleteLeasing(leasingId);

        // Validation
        // Tjekker at controlleren har kalt metoden "delete()" i repositorien.
        verify( dataRegistrationRepository).delete(leasingId);
        // Tjekker at metoden returnerer den rigtige URL.
        assertEquals("redirect:/dataRegistrationAllLeasings?user_role=data_registration", result);
    }
    @Test
    @DisplayName("Update Leasing Happy Flow Test")
    public void updateLeasingHappyFlow_Test() {
        //Assumptions
        int leasingId = 1;
        Leasing leasing = new Leasing(leasingId,12,LocalDate.of(2025,05,20), LocalDate.of(2025,10,20), 100000, false, "test1");

        when(dataRegistrationRepository.getLeasingByID(leasingId)).thenReturn(leasing);

        //Execution
        String result = dataRegistrationController.updateLeasing(leasingId,model);

        //Validation
        assertEquals("dataRegistrationUpdateLeasing", result);
        verify(model).addAttribute("leasing", leasing);
    }

    @Test
    @DisplayName("Update Leasing Exception Flow Test")
    public void updateLeasingExceptionFlow_Test() {
        //Assumptions
        int leasingId = 1;
        when(dataRegistrationRepository.getLeasingByID(leasingId)).thenReturn(null);

        //Execution
        String result = dataRegistrationController.updateLeasing(leasingId,model);

        //Validation
        assertEquals("redirect:/dataRegistrationAllLeasings?user_role=data_registration",result);
        assertTrue(model.asMap().isEmpty(), "Model should be empty");
    }
}
