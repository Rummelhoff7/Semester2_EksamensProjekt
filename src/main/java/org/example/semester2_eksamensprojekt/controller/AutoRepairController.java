package org.example.semester2_eksamensprojekt.controller;

import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.example.semester2_eksamensprojekt.repository.AutoRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;


@Controller
public class AutoRepairController {

    @Autowired
    AutoRepairRepository autoRepairRepository;


    @GetMapping("/autoRepair")
    public String mainPage(){
        return "autoRepair";
    }

    @PostMapping ("/saveDamageReport")
    public String saveDamageReport(@RequestParam ("id")int id,
                                   @RequestParam ("car_id") int car_id,
                                   @RequestParam ("date") LocalDate date){

        DamageReport damageReport = new DamageReport(id, car_id, date);
        autoRepairRepository.save(damageReport);
        return "redirect:/autoRepair";




    }
}


