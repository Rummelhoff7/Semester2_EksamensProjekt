package org.example.semester2_eksamensprojekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    //Thamid
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    //Thamid
    //Denne Getmapping er for vores logout button
    @GetMapping("/index")
    public String logOut(){
        return "index";
    }
}

