package org.example.semester2_eksamensprojekt.controller;

import ch.qos.logback.core.model.Model;
import org.example.semester2_eksamensprojekt.model.AdvanceCarSale;
import org.example.semester2_eksamensprojekt.repository.AdvanceCarSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvanceCarSaleController {

    @Autowired
    AdvanceCarSaleRepository advanceCarSaleRepository;

    @GetMapping("/advanceCarSale")
    public String advanceCarSalePage() {
        return "advanceCarSale"; // Name of your Thymeleaf template without .html
    }


    @PostMapping("/saveCreateAdvanceCarSale")
    public String postCreateAdvanceCarSale(@RequestParam("car_id")int car_id,
                                            @RequestParam ("terms") String terms,
                                            @RequestParam ("exceeded_kilometers") int exceeded_kilometers,
                                           @RequestParam ("collection_point") String collection_point)
    {


        AdvanceCarSale advanceCarSale = new AdvanceCarSale(car_id, terms, exceeded_kilometers, collection_point);

        advanceCarSaleRepository.save(advanceCarSale);
        return "redirect:/dataRegistration?user_role=" + "data_registration";

    }
}
