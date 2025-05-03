package org.example.semester2_eksamensprojekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutoRepairController {

    @GetMapping("/autoRepair")
    public String autoRepairPage(@RequestParam("user_role") String user_role, Model model) {
        //Her tjekker den om url har den rigtig user_role og sender en tilbage til start siden hvis den ikke har. Med en errorMessage
        if(user_role.equals("admin")) {
            return "autoRepair";
        } else if (user_role.equals("mechanic")) {
            return "autoRepair";

        } else
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
        return "index";
    }
}
