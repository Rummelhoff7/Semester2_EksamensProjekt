package org.example.semester2_eksamensprojekt.controller;

import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.example.semester2_eksamensprojekt.repository.AutoRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AutoRepairController {

    @Autowired
    AutoRepairRepository autoRepairRepository;

    @GetMapping("/autoRepair")
    public String autoRepairPage(@RequestParam("user_role") String user_role, Model model) {
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if (user_role.equals("admin")) {
            return "autoRepair";
        } else if (user_role.equals("mechanic")) {
            return "autoRepair";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvede at komme ind på");
            return "index";
        }
    }
// får besked fra frontend om at gemme Skadesrapport via to parametre, og metoden bliver kaldt i repository
    @PostMapping("/saveDamageReport")
    public String saveDamageReport(@RequestParam("car_id") int car_id,
                                   @RequestParam("date") LocalDate date) {
        DamageReport damageReport = new DamageReport(car_id, date);
        autoRepairRepository.save(damageReport);
        return "redirect:/damageItems?car_id="+car_id;
    }
// får besked fra frontend om at gemme Skader, via metoden som bliver kaldt i repository
    @PostMapping("/saveDamageItems")
    public String saveDamageItem(@RequestParam("dmg_id") int dmg_id,
                                   @RequestParam("description") String description,
                                    @RequestParam("cost") double cost) {

        DamageItem damageItem = new DamageItem(dmg_id,description,cost);
        autoRepairRepository.saveDamageItem(damageItem);
        return "redirect:/damageItems?car_id="+dmg_id;
    }





    @GetMapping("/damageItems")
    public String showDamageItemForm(@RequestParam("car_id") int dmg_id, Model model) {
        model.addAttribute("dmg_id", dmg_id);
        return "damageItems";
    }
// sletter skadserapport ud fra parameteret car_id
    @PostMapping("/deleteDamageReport")
    public String deleteDamageReport(@RequestParam("car_id") int car_id) {
        autoRepairRepository.delete(car_id);
        return "autoRepair";
    }



}
