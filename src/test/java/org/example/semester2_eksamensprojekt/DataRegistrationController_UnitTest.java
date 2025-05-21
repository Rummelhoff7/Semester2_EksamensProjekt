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
    private DataRegistrationRepository dataRegistrationRepository;

    @Mock
    private Model model;

    @InjectMocks   //Fortæller mockito dette skal mockes, så den ved den ikke skal fungere som på normal vis.
    private DataRegistrationController dataRegistrationController;

    @Test
    @DisplayName("deleteLeasing Happy flow test")
    public void deleteLeasingHappyFlow_Test(){
        //Assumptions;
        int leasingId = 1;
        //Execution
        String result = dataRegistrationController.deleteLeasing(leasingId);
        // Validation
        verify(dataRegistrationRepository).delete(leasingId);
        assertEquals("redirect:/dataRegistrationAllLeasings?user_role=data_registration", result);
    }

    @Test
    @DisplayName("deleteLeasing Exception flow test")
    public void deleteLeasingExceptionFlow_Test(){
        // Assumptions;
        int leasingId = 1;
        //given(dataRegistrationController.deleteLeasing(leasingId)).willReturn(null);

        // Execution
        String result = dataRegistrationController.deleteLeasing(leasingId);

        // Validation
        assertEquals( "redirect:/dataRegistrationAllLeasings?user_role=data_registration", result);
        assertTrue(model.asMap().isEmpty(), "Model should be empty");
    }

    @Test
    @DisplayName("Update Leasing Happy Flow Test")
    public void updateLeasingHappyFlow_Test() {
        //Assumptions
        int leasingId = 1;
        Leasing leasing = new Leasing(leasingId,12,LocalDate.of(2025,05,20), LocalDate.of(2025,10,20), 100000, false, "test1");

        when(dataRegistrationRepository.getLeasingByID(leasingId)).thenReturn(leasing);

        //Excecution
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

        //Excecution
        String result = dataRegistrationController.updateLeasing(leasingId,model);

        //Validation
        assertEquals("redirect:/dataRegistrationAllLeasings?user_role=data_registration",result);
        assertTrue(model.asMap().isEmpty(), "Model should be empty");
    }

    @Test
    @DisplayName("")
    public void dataRegistrationAllLeasings_HappyFlow_Test() {
        //Assumptions
        String user_role = "data_registration";

        ArrayList<Leasing> mockLeasingList = new ArrayList<>();
        mockLeasingList.add(new Leasing(1,12,LocalDate.of(2025,05,20), LocalDate.of(2025,10,20), 100000, false, "test1"));
        mockLeasingList.add(new Leasing(2,10,LocalDate.of(2025,05,20), LocalDate.of(2025,10,20), 100000, false, "test2"));
        when(dataRegistrationRepository.getAllLeasings()).thenReturn(mockLeasingList);

        //Excecution
        String result = dataRegistrationController.dataRegistrationAllLeasings(user_role, model);

        //Validation
        assertEquals( "dataRegistrationAllLeasings", result);
        verify(model).addAttribute("leasingList", mockLeasingList);
    }

    @Test
    @DisplayName("")
    public void dataRegistrationAllLeasings_ExceptionFlow_Test() {
        //Assumptions
        String user_role = "data_registration";

        when(dataRegistrationRepository.getAllLeasings()).thenReturn(null);

        //Excecution
        String result = dataRegistrationController.dataRegistrationAllLeasings(user_role, model);


        //Validation
        assertEquals("redirect:/dataRegistrationHomePage?user_role=data_registration" , result);
        verify(model,never()).addAttribute(anyString(), any()); //verify ikke at model har en attribut med navnet "leasingList", ingen attributter tilføjet.
    }
}
