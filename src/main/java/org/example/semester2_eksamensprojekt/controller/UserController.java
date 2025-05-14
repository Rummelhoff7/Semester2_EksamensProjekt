package org.example.semester2_eksamensprojekt.controller;
import org.example.semester2_eksamensprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin")
    public String adminPage(@RequestParam ("user_role") String user_role, Model model) {
        //Her tjekker den om url har den rigtig user_role og sender en tilbage til start siden hvis den ikke har. Med en errorMessage
        if(user_role.equals("admin")) {
            return "admin";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String userRole = userRepository.authenticateUser(username, password);

        // her tjekker switch hvilken rolle user har og sender dem til den rigtig side med dens userRole i url
        switch (userRole) {
            case "data_registration": return "redirect:/dataRegistration?user_role="+userRole;
            case "business_developer": return "redirect:/businessDeveloper?user_role="+userRole;
            case "mechanic": return "redirect:/autoRepair?user_role="+userRole;
            case "admin": return "redirect:/admin?user_role="+userRole;

            // Hvis brugeren eller password er forkert får man en errorMessage
            default:
                model.addAttribute("errorMessage", "Forket brugernavn eller password. Prøv igen.");
                return "index";
        }
    }
}
