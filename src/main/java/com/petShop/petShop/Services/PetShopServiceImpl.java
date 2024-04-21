package com.petShop.petShop.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petShop.petShop.Entities.Pet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class PetShopServiceImpl implements PetShopService {


    @Override
    public List<Pet> findByStatus() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String soldPetsUrl = "https://petstore.swagger.io/v2/pet/findByStatus?status=sold";
        ObjectMapper objectMapper = new ObjectMapper();
        String url = UriComponentsBuilder.fromUriString(soldPetsUrl).toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        JsonNode responseNode = objectMapper.readTree(jsonResponse);
        List<Pet>pets = parseJson(responseNode.toString());
        System.out.println("Lista de mascotas vendidas:");
        pets.forEach(pet->System.out.println(pet.getId()+":"+pet.getName().toUpperCase()));
        return pets;
    }


    @Override
    public Map<String, Integer> countNames() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> nameCounts = new HashMap<>();
        String soldPetsUrl = "https://petstore.swagger.io/v2/pet/findByStatus?status=available,pending,sold";
        ObjectMapper objectMapper = new ObjectMapper();
        String url = UriComponentsBuilder.fromUriString(soldPetsUrl).toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        JsonNode responseNode = objectMapper.readTree(jsonResponse);
        List<Pet>pets = parseJson(responseNode.toString());

        for (Pet pet : pets) {
            String name = pet.getName().toUpperCase();
            nameCounts.put(name, nameCounts.getOrDefault(name, 0) + 1);
        }
        return nameCounts;
    }

    public List<Pet> parseJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readValue(json, JsonNode.class);

        List<Pet> pets = new ArrayList<>();
        for (JsonNode petNode : rootNode) {
            long id = petNode.path("id").asLong();
            String name = petNode.path("name").asText();
            Pet pet = new Pet(id, name);
            pets.add(pet);
        }

        return pets;
    }
}
