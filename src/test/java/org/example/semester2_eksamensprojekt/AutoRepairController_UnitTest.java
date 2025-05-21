package org.example.semester2_eksamensprojekt;

import org.example.semester2_eksamensprojekt.controller.AutoRepairController;
import org.example.semester2_eksamensprojekt.controller.DataRegistrationController;
import org.example.semester2_eksamensprojekt.repository.AutoRepairRepository;
import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


@ExtendWith(MockitoExtension.class)
public class AutoRepairController_UnitTest {

    @Mock //SQL- strengen vil ikke integreres i unit test
    private AutoRepairRepository autoRepairRepository;

    @Mock
    private Model model;

    @InjectMocks   //Fortæller mockito dette skal mockes, så den ved den ikke skal fungere som på normal vis.
    private AutoRepairController autoRepairController;

    @Test
    @DisplayName("")
    public void showDamageItemForm_HappyFlow_Test() {


        //Assumptions

        //Execution

        //Validation
    }

    @Test
    @DisplayName("")
    public void showDamageItemForm_ExceptionFlow_Test() {
//Assumptions

        //Execution

        //Validation
    }

}
