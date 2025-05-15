package org.example.semester2_eksamensprojekt.controller;
import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.repository.CarRepository;
import org.springframework.ui.Model;
import org.example.semester2_eksamensprojekt.model.Leasing;
import org.example.semester2_eksamensprojekt.repository.DataRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class DataRegistrationController {


    @Autowired
    DataRegistrationRepository dataRegistrationRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping("/dataRegistrationHomePage")
    public String mainPage(@RequestParam ("user_role") String user_role, org.springframework.ui.Model model){
        if(user_role.equals("data_registration") || user_role.equals("admin")) {

            ArrayList<Car> carForSale = carRepository.getAllLimitedLeasing();
            model.addAttribute("carForSale", carForSale);

            return "dataRegistrationHomePage";
        } else {

            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }
    }


        @GetMapping("/dataRegistration")
        public String dataRegistration(@RequestParam ("user_role") String user_role, org.springframework.ui.Model model){
        if(user_role.equals("data_registration") || user_role.equals("admin")) {
            return "dataRegistration";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }
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


    @PostMapping("/deleteLeasing")
    public String deleteLeasing(@RequestParam("id") int id){
        dataRegistrationRepository.delete(id);

        return "redirect:/dataRegistrationAllLeasings?user_role=data_registration";
    }


    @PostMapping("/saveUpdateLeasing")
    public String postUpdateLeasing (@RequestParam("id") int id,
                                     @RequestParam("car_id") int car_id,
                                     @RequestParam("start_date") LocalDate start_date,
                                     @RequestParam("end_date") LocalDate end_date,
                                     @RequestParam("price") double price,
                                     // Denne linje kode har været nødvendig, for at undgå whitelabel error. value attributten specificerer navnet på RequestParam.
                                     // dvs. "value = "status" matcher med name="status" i dataRegistrationUpdateLeasing.html.
                                     // defaultValue = "false" sikrer, at hvis "status" ikke sendes fra formularen, bliver den automatisk sat til false i metoden.(det var her whitelabel error var et problem)
                                     // boolean status modtager derfor enten true (checked) eller false (unchecked).
                                     @RequestParam(value = "status", defaultValue = "false") boolean status,
                                     @RequestParam("customer_info") String customer_info){

        //Logger for respons fra terminalen
        System.out.println("Status:" + status);

        Leasing leasing = new Leasing (id, car_id, start_date, end_date, price, status, customer_info);
        dataRegistrationRepository.update(leasing);

        return "redirect:/dataRegistrationAllLeasings?user_role=data_registration";
    }


    @GetMapping("/dataRegistrationAllLeasings")
    public String dataRegistrationAllLeasings(@RequestParam ("user_role") String user_role, Model model){
        if(user_role.equals("data_registration") || user_role.equals("admin")) {
            ArrayList<Leasing> leasingList;
            leasingList = dataRegistrationRepository.getAllLeasings();
            model.addAttribute("leasingList", leasingList);
            return "dataRegistrationAllLeasings";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }

    }



    @GetMapping("/getUpdateLeasing")
    public String updateLeasing(@RequestParam("id") int id, Model model) {
        Leasing leasing = dataRegistrationRepository.getLeasingByID(id);
        model.addAttribute("leasing",leasing);
        return "dataRegistrationUpdateLeasing";
    }
}
