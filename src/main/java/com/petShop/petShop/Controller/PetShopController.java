package com.petShop.petShop.Controller;

import com.petShop.petShop.Entities.Pet;
import com.petShop.petShop.Services.PetShopServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet-shop")
@CrossOrigin("*")
public class PetShopController {
    private final PetShopServiceImpl petShopService;

    public PetShopController(PetShopServiceImpl petShopService) {
        this.petShopService = petShopService;
    }

    @GetMapping
    public List<Pet> getPetsSold() throws IOException {
        return petShopService.findByStatus();
    }

    @GetMapping("/count-names")
    public Map<String, Integer> countNames() throws IOException {
        return petShopService.countNames();
    }



}
