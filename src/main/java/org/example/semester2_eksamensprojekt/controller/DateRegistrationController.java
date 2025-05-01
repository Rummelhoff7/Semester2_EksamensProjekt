package org.example.semester2_eksamensprojekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateRegistrationController {

    @GetMapping("/dataRegistration")
    public String mainPage(){
        return "dataRegistration";
    }
}
