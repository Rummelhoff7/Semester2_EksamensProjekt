package org.example.semester2_eksamensprojekt.controller;

import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.example.semester2_eksamensprojekt.model.DamageReport;
import org.example.semester2_eksamensprojekt.repository.AutoRepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
public class AutoRepairController {

    @Autowired
    AutoRepairRepository autoRepairRepository;

    @GetMapping("/autoRepairHomePage")
    public String autoRepairPage(@RequestParam("user_role") String user_role, Model model) {
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if (user_role.equals("admin")) {
            model.addAttribute("adminBtn", "<a class=button-row th:href=@{/admin(user_role=admin)}>Gå tilbage</a>");
            return "autoRepairHomePage";
        }
        else if (user_role.equals("mechanic")){
            return "autoRepairHomePage";
        }else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvede at komme ind på");
            return "index";
        }
    }

    @GetMapping("/updateDamageReport")
    public String updateDamageReport(@RequestParam("id") int id, Model model) {
        DamageReport damageReport = autoRepairRepository.getDamageReportById(id);
        model.addAttribute("damageReport", damageReport);
        return "updateDamageReport"; // navnet på din HTML-template til opdatering
    }




    @GetMapping("/autoRepair")
    public String autoRepair(@RequestParam("user_role") String user_role, Model model) {
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


    @GetMapping("/showDamagereports")
    public String showAllDamageReports(@RequestParam("user_role") String user_role, Model model) {
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if (!(user_role.equals("admin") || user_role.equals("mechanic"))) {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvede at komme ind på");
            return "index";
        }

        List<DamageReport> reportList = autoRepairRepository.getAllDamageReports();
        for (DamageReport report : reportList) {
            report.setDamageItems(autoRepairRepository.getDamageItemsByReportId(report.getId()));
        }

        model.addAttribute("reportList", reportList);
        return "showDamagereports";
    }

    @PostMapping("/saveDamageReport")
    public String saveDamageReport(@RequestParam("car_id") int car_id,
                                   @RequestParam("date") LocalDate date) {
        DamageReport damageReport = new DamageReport(car_id, date);
        int Damagereport_id= autoRepairRepository.save(damageReport);
        return "redirect:/damageItems?dmg_id=" + Damagereport_id;
    }

    @PostMapping("/saveDamageItems")
    public String saveDamageItem(@RequestParam("dmg_id") int dmg_id,
                                 @RequestParam("description") String description,
                                 @RequestParam("cost") double cost) {

        DamageItem damageItem = new DamageItem(dmg_id, description, cost);
        autoRepairRepository.saveDamageItem(damageItem);
        return "redirect:/damageItems?dmg_id=" + dmg_id;
    }

    @GetMapping("/damageItems")
    public String showDamageItemForm(@RequestParam("dmg_id") int dmg_id, Model model) {
        model.addAttribute("dmg_id", dmg_id);
        return "damageItems";
    }

    @PostMapping("/deleteDamageReport")
    public String deleteDamageReport(@RequestParam("id") int id) {
        autoRepairRepository.deleteDamageItem(id);
        autoRepairRepository.delete(id);
        return "redirect:/showDamagereports?user_role=admin";
    }
}
