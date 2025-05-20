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
        if(user_role.equals("admin")) {

            // Her ser vi hvor mange biler der er udlejet og får tallet fra rentedcars metoden
            int totalRentedCars = businessDeveloperRepository.rentedcars();
            model.addAttribute("totalRentedCars", totalRentedCars);

            // Her ligger vi alle leasing aftalers indtjening sammen i totalamount og får total indtjeningen.
            double totalAmount = businessDeveloperRepository.totalamount();
            model.addAttribute("totalAmount", totalAmount);

            // Her får vi et Array med alle de udlejet biler
            ArrayList<Car> rentedCarList = businessDeveloperRepository.getAllRentedCars();
            model.addAttribute("rentedCarList", rentedCarList);

            // Her får vi et Array med alle ledige biler
            ArrayList<Car> availableCarList = businessDeveloperRepository.getAllAvailableCars();
            model.addAttribute("availableCarList", availableCarList);
            model.addAttribute("adminBtn", "<a class=button-row th:href=@{/admin(user_role=admin)}>Gå tilbage</a>");

            return "businessDeveloper";
        }
        else if(user_role.equals("business_developer")){


            // Her ser vi hvor mange biler der er udlejet og får tallet fra rentedcars metoden
            int totalRentedCars = businessDeveloperRepository.rentedcars();
            model.addAttribute("totalRentedCars", totalRentedCars);

            // Her ligger vi alle leasing aftalers indtjening sammen i totalamount og får total indtjeningen.
            double totalAmount = businessDeveloperRepository.totalamount();
            model.addAttribute("totalAmount", totalAmount);

            // Her får vi et Array med alle de udlejet biler
            ArrayList<Car> rentedCarList = businessDeveloperRepository.getAllRentedCars();
            model.addAttribute("rentedCarList", rentedCarList);

            // Her får vi et Array med alle ledige biler
            ArrayList<Car> availableCarList = businessDeveloperRepository.getAllAvailableCars();
            model.addAttribute("availableCarList", availableCarList);

            return "businessDeveloper";
        }
        else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøver at komme ind på");
            return "index";
        }
    }
}
