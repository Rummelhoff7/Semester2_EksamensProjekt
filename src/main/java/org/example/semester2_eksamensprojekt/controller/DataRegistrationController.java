package org.example.semester2_eksamensprojekt.controller;

import ch.qos.logback.core.model.Model;
import org.example.semester2_eksamensprojekt.model.Leasing;
import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class DataRegistrationController {

    @Autowired
    DataRegistrationRepository dataRegistrationRepository;

    @GetMapping("/dataRegistration")
    public String mainPage(){
        return "dataRegistration";
    }


    // PostMapping hånder HTTP Post-anmodninger. Her opretter vi en ny leasing og gemmer den i databasen.
    // Vi bruger en metode fra DataRegistrationRepository for at gemme.
    @PostMapping("createLeasing")
    // Connecter RequestParameter med leasing attributer.
    public String createLeasing(@RequestParam ("car_id") int car_id,
                                @RequestParam ("start_date") LocalDate start_date,
                                @RequestParam ("end_date") LocalDate end_date,
                                @RequestParam ("price") double price,
                                @RequestParam ("status") boolean status,
                                @RequestParam ("customer_info") String customer,
                                Model model) {

        // Opretter en ny Leasing-objekt, som indeholder de nye attributer.
        Leasing leasing = new Leasing(car_id,start_date, end_date, price, status, customer);

        // Kalder metode fra repository, som gemmer objektet i databasen.
        dataRegistrationRepository.save(leasing);
        // Returnerer til dataRegistration siden.
        return "redirect:/dataRegistration";
    }
}
