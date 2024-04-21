package com.petShop.petShop.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petShop.petShop.Entities.Pet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PetShopService {

    List<Pet> findByStatus() throws JsonProcessingException;
    Map<String, Integer> countNames() throws JsonProcessingException;

}
