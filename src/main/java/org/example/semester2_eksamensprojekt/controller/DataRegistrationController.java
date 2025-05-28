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

    // user.Role håndtering -> = Joakim

    //Mads
    @GetMapping("/dataRegistrationHomePage")
    public String mainPage(@RequestParam ("user_role") String user_role, Model model){
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if(user_role.equals("admin")) {
            ArrayList<Car> carForSale = carRepository.getAllLimitedLeasing();
            model.addAttribute("carForSale", carForSale);
            //Her sørger vi for der er en knap der får en tilbage på admin.html hvis de er admin
            model.addAttribute("adminBtn", "<a class=button-row th:href=@{/admin(user_role=admin)}>Gå tilbage</a>");
            return "dataRegistrationHomePage";
        }
        else if(user_role.equals("data_registration")){
            ArrayList<Car> carForSale = carRepository.getAllLimitedLeasing();
            model.addAttribute("carForSale", carForSale);
            return "dataRegistrationHomePage";
        }else {

            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }
    }
        //Mads
    @GetMapping("/dataRegistration")
    public String dataRegistration(@RequestParam ("user_role") String user_role, org.springframework.ui.Model model){
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if(user_role.equals("data_registration") || user_role.equals("admin")) {
            return "dataRegistration";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }
    }

    //Mads
    // PostMapping hånder HTTP Post-anmodninger. Her opretter vi en ny leasing og gemmer den i databasen.
    // Vi bruger en metode fra DataRegistrationRepository for at gemme.
    @PostMapping("/createLeasing")
    // Connecter RequestParameter med leasing attributer.
    public String createLeasing(@RequestParam ("car_id") int car_id,
                                @RequestParam ("start_date") LocalDate start_date,
                                @RequestParam(value = "end_date", required = false) LocalDate end_date,
                                @RequestParam ("price") double price,
                                @RequestParam(value = "status", defaultValue = "false") boolean status,
                                @RequestParam ("customer_info") String customer,
                                Model model) {

        // Metode, der tjekker om bilen findes.
        if (!dataRegistrationRepository.carExists(car_id)) {
            model.addAttribute("errorMessage", "Der findes ikke en bil med dette vognnummer");
            return "dataRegistration";
        }
        // Metode, der tjekker om leasing allerede findes
        if (dataRegistrationRepository.leasingExistsForCar(car_id)) {
            model.addAttribute("errorMessage", "Der findes allerede en leasing på denne bil");
            return "dataRegistration";
        }

        // Metode, som tjekker om regner slutdato ud ift. abonnement.
        try {
            end_date = dataRegistrationRepository.calculateEndDate(start_date, end_date, status);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Abonnement skal minimum være på 3 måneder fra startdatoen.");
            return "dataRegistration";
        }

        // Opretter en ny Leasing-objekt, som indeholder de nye attributer.
        Leasing leasing = new Leasing(car_id,start_date, end_date, price, status, customer);

        // Kalder metode fra repository, som gemmer objektet i databasen.
        dataRegistrationRepository.save(leasing);
        // Returnerer til dataRegistration siden.
        return "redirect:/dataRegistrationHomePage?user_role=data_registration";
    }

    //Mads
    @PostMapping("/deleteLeasing")
    public String deleteLeasing(@RequestParam("id") int id){
        // Laver en try catch til at slette objektet i databasen, dette er gjort ift. exception flow test eksempel.
        try {
            dataRegistrationRepository.delete(id);
        } catch (Exception e) {
            // error println.
            System.err.println("Kunne ikke slette leasing med id: " + id);
        }

        return "redirect:/dataRegistrationAllLeasings?user_role=data_registration";
    }

    //Mads
    @PostMapping("/saveUpdateLeasing")
    public String postUpdateLeasing (@RequestParam("id") int id,
                                     @RequestParam("car_id") int car_id,
                                     @RequestParam("start_date") LocalDate start_date,
                                     @RequestParam(value = "end_date", required = false) LocalDate end_date,
                                     @RequestParam("price") double price,
                                     // Denne linje kode har været nødvendig, for at undgå whitelabel error. value attributten specificerer navnet på RequestParam.
                                     // dvs. "value = "status" matcher med name="status" i dataRegistrationUpdateLeasing.html.
                                     // defaultValue = "false" sikrer, at hvis "status" ikke sendes fra formularen, bliver den automatisk sat til false i metoden.(det var her whitelabel error var et problem)
                                     // boolean status modtager derfor enten true (checked) eller false (unchecked).
                                     @RequestParam(value = "status", defaultValue = "false") boolean status,
                                     @RequestParam("customer_info") String customer_info,
                                     Model model){
        // Her er det nødvendigt at tilføje ny leasing på alle disse metoder, da objektet ikke skal forsvinde, hvis metoderne bliver kaldt.
        // Metode, der tjekker om bilen findes.
        if (!dataRegistrationRepository.carExists(car_id)) {
            model.addAttribute("errorMessage", "Der findes ikke en bil med dette vognnummer");
            model.addAttribute("leasing", new Leasing(id, car_id, start_date, end_date, price, status, customer_info));
            return "dataRegistrationUpdateLeasing";
        }

        // Metode, som tjekker om regner slutdato ud ift. abonnement.
        if (dataRegistrationRepository.leasingExistsForCarExcludingId(car_id, id)) {
            model.addAttribute("errorMessage", "Der findes allerede en leasing på denne bil");
            model.addAttribute("leasing", new Leasing(id, car_id, start_date, end_date, price, status, customer_info));
            return "dataRegistrationUpdateLeasing";
        }

        // Opretter en ny Leasing-objekt, som indeholder de nye attributer.
        try {
            end_date = dataRegistrationRepository.calculateEndDate(start_date, end_date, status);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Abonnement skal minimum være på 3 måneder fra startdatoen.");
            model.addAttribute("leasing", new Leasing(id, car_id, start_date, end_date, price, status, customer_info));
            return "dataRegistrationUpdateLeasing";
        }

        // Opretter ny leasing objekt, og sætter ind i database.
        Leasing leasing = new Leasing (id, car_id, start_date, end_date, price, status, customer_info);
        dataRegistrationRepository.update(leasing);

        return "redirect:/dataRegistrationAllLeasings?user_role=data_registration";
    }

    //Mads
    @GetMapping("/dataRegistrationAllLeasings")
    public String dataRegistrationAllLeasings(@RequestParam ("user_role") String user_role, Model model){
        // Tjekker den om url har den rigtige user_role og sender den tilbage til start hvis den ikke har. Med en errormessage
        if(user_role.equals("data_registration") || user_role.equals("admin")) {
            ArrayList<Leasing> leasingList;
            leasingList = dataRegistrationRepository.getAllLeasings();

            //bruges ift. exception flow test.
            if (leasingList == null || leasingList.isEmpty() ) {
                return "redirect:/dataRegistrationHomePage?user_role=" + user_role;
            }

            model.addAttribute("leasingList", leasingList);
            return "dataRegistrationAllLeasings";
        } else {
            model.addAttribute("errorMessage", "Den rolle passer ikke til den side du prøvet at komme ind på");
            return "index";
        }

    }


    //Mads
    // GetMapping for opdater leasing. Den skal have et id, som bliver fundet af metode.
    // Hvis leasing ikke bliver eksisterer, redirecter vi til http siden med alle leasingerne.
    @GetMapping("/getUpdateLeasing")
    public String updateLeasing(@RequestParam("id") int id, Model model) {
        Leasing leasing = dataRegistrationRepository.getLeasingByID(id);
        if (leasing == null) {
            return "redirect:/dataRegistrationAllLeasings?user_role=data_registration";
        }
        // Leasing objektet bliver hentet fra database, og tilføjes til model. Gøres tilgængelig i html (spring)
        model.addAttribute("leasing",leasing);
        return "dataRegistrationUpdateLeasing";
    }
}
