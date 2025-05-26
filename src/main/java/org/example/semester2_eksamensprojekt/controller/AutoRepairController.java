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

    // Viser startsiden for autoværksted afhængigt af brugerrolle (admin eller mekaniker)
    @GetMapping("/autoRepairHomePage")
    public String autoRepairPage(@RequestParam("user_role") String user_role, Model model) {
        // Hvis brugeren er admin, tilføj en admin-knap og vis siden
        if (user_role.equals("admin")) {
            model.addAttribute("adminBtn", "<a class=button-row th:href=@{/admin(user_role=admin)}>Gå tilbage</a>");
            return "autoRepairHomePage";
        }
        // Hvis brugeren er mekaniker, vis siden uden admin-knappen
        else if (user_role.equals("mechanic")) {
            return "autoRepairHomePage";
        }
        // Hvis ingen af delene, vis fejlbesked og send brugeren tilbage til login
        else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvede at komme ind på");
            return "index";
        }
    }

    // Viser formular til opdatering af en eksisterende skadesrapport
    @GetMapping("/updateDamageReport")
    public String updateDamageReport(@RequestParam("id") int id, Model model) {
        DamageReport damageReport = autoRepairRepository.getDamageReportById(id);
        model.addAttribute("damageReport", damageReport);
        return "updateDamagereport";
    }

    // Viser tom autioRepair-siden
    @GetMapping("/autoRepair")
    public String autoRepair(@RequestParam("user_role") String user_role, Model model) {
        if (user_role.equals("admin")) {
            return "autoRepair";
        } else if (user_role.equals("mechanic")) {
            return "autoRepair";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvede at komme ind på");
            return "index";
        }
    }

    // Viser alle skadesrapporter og tilføjer tilhørende skader til hver rapport
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

    // Gemmer en ny skadesrapport i databasen og sender brugeren videre til damageItems-formularen
    @PostMapping("/saveDamageReport")
    public String saveDamageReport(@RequestParam("car_id") int car_id,
                                   @RequestParam("date") LocalDate date) {
        DamageReport damageReport = new DamageReport(car_id, date);
        int Damagereport_id = autoRepairRepository.save(damageReport);
        return "redirect:/damageItems?dmg_id=" + Damagereport_id;
    }

    // Gemmer en enkelt skade til en bestemt rapport
    @PostMapping("/saveDamageItems")
    public String saveDamageItem(@RequestParam("dmg_id") int dmg_id,
                                 @RequestParam("description") String description,
                                 @RequestParam("cost") double cost) {

        DamageItem damageItem = new DamageItem(dmg_id, description, cost);
        autoRepairRepository.saveDamageItem(damageItem);
        return "redirect:/damageItems?dmg_id=" + dmg_id;
    }

    // Viser formular til at tilføje skader
    @GetMapping("/damageItems")
    public String showDamageItemForm(@RequestParam("dmg_id") int dmg_id, Model model) {
        model.addAttribute("dmg_id", dmg_id);
        return "damageItems";
    }

    // Sletter en hel skadesrapport og tilknyttede skader
    @PostMapping("/deleteDamageReport")
    public String deleteDamageReport(@RequestParam("id") int id) {
        autoRepairRepository.deleteDamageItem(id);
        autoRepairRepository.delete(id);
        return "redirect:/showDamagereports?user_role=admin";
    }

    // Gemmer opdateringer til en eksisterende skadesrapport
    @PostMapping("/saveUpdateDamageReport")
    public String saveUpdateDamageReport(@RequestParam("id") int id,
                                         @RequestParam("car_id") int car_id,
                                         @RequestParam("date") LocalDate date) {

        // Opretter et opdateret DamageReport-objekt med nye værdier
        DamageReport updatedReport = new DamageReport(id, car_id, date);

        // Kalder repository-metode, der opdaterer databasen med de nye værdier
        autoRepairRepository.updateDamageReport(updatedReport);

        // Returnerer brugeren til oversigten over rapporter efter opdatering
        return "redirect:/showDamagereports?user_role=admin";
    }

}
