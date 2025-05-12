package org.example.semester2_eksamensprojekt.controller;

import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.repository.BusinessDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class BusinessDeveloperController {

    @Autowired
    private BusinessDeveloperRepository businessDeveloperRepository;


    @GetMapping("/businessDeveloper")
    public String businessDeveloperPage(@RequestParam("user_role") String user_role, Model model) {
        //Her tjekker den om url har den rigtig user_role og sender en tilbage til start siden hvis den ikke har. Med en errorMessage
        if(user_role.equals("admin") || user_role.equals("business_developer")) {
            int totalRentedCars = businessDeveloperRepository.rentedcars();
            model.addAttribute("totalRentedCars", totalRentedCars);

            double totalAmount = businessDeveloperRepository.totalamount();
            model.addAttribute("totalAmount", totalAmount);

            ArrayList<Car> rentedCarList = businessDeveloperRepository.getAllRentedCars();
            model.addAttribute("rentedCarList", rentedCarList);

            ArrayList<Car> availablecarList = businessDeveloperRepository.getAllAvailableCars();
            model.addAttribute("availablecarList", availablecarList);


            return "businessDeveloper";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøver at komme ind på");
            return "index";
        }
    }
}
