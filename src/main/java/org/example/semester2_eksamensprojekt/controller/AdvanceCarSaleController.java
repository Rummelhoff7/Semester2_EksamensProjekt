package org.example.semester2_eksamensprojekt.controller;

import org.example.semester2_eksamensprojekt.model.AdvanceCarSale;
import org.example.semester2_eksamensprojekt.model.Car;
import org.example.semester2_eksamensprojekt.model.CarSalesInfo;
import org.example.semester2_eksamensprojekt.model.DamageItem;
import org.example.semester2_eksamensprojekt.repository.AdvanceCarSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;


@Controller
public class AdvanceCarSaleController {

    @Autowired
    AdvanceCarSaleRepository advanceCarSaleRepository;

    @GetMapping("/advanceCarSale")
    public String advanceCarSalePage() {
        return "advanceCarSale";
    }


    @PostMapping("/saveCreateAdvanceCarSale")
    public String postCreateAdvanceCarSale(@RequestParam("car_id")int car_id,
                                            @RequestParam ("terms") String terms,
                                            @RequestParam ("exceeded_kilometers") int exceeded_kilometers,
                                           @RequestParam ("collection_point") String collection_point)
    {


        AdvanceCarSale advanceCarSale = new AdvanceCarSale(car_id, terms, exceeded_kilometers, collection_point);

        advanceCarSaleRepository.save(advanceCarSale);
        return "redirect:/advanceCarSalePrice?car_id=" + car_id;

    }

    @GetMapping("/advanceCarSalePrice")
    public String advanceCarSalePricePage(@RequestParam("car_id")int car_id, Model model) {
        CarSalesInfo salesInfo = advanceCarSaleRepository.save_price(car_id);

        if (salesInfo != null) {
            model.addAttribute("total_price", salesInfo.getFinalPrice());
            model.addAttribute("exceeded_km_cost", salesInfo.getExceededKmCost());
        } else {
            model.addAttribute("total_price", 0);
            model.addAttribute("exceeded_km_cost", 0);
        }

        int exceeded_kilometers = advanceCarSaleRepository.exceeded_kilometers(car_id);
        model.addAttribute("exceeded_kilometers", exceeded_kilometers);

        ArrayList<DamageItem> DamageitemList = advanceCarSaleRepository.getAllDamageItemWithCarID(car_id);
        model.addAttribute("DamageitemList", DamageitemList);


        return "advanceCarSalePrice";

    }

    @PostMapping("/saveTotalPrice")
    public String saveTotalPrice(@RequestParam int car_id,
                                 @RequestParam double total_price) {

        AdvanceCarSale advanceCarSale = new AdvanceCarSale(car_id,total_price);

        advanceCarSaleRepository.saveTotalPrice(advanceCarSale);

        return  "redirect:/dataRegistration?user_role=data_registration";
    }
}
