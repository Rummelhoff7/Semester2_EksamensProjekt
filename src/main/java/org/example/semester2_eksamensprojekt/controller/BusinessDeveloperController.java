package org.example.semester2_eksamensprojekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BusinessDeveloperController {


    @GetMapping("/businessDeveloper")
    public String mainPage(){
        return "businessDeveloper";
    }
}
