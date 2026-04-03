package com.dropp.foodcatalog.controller;

import com.dropp.foodcatalog.dto.FoodCatalogPage;
import com.dropp.foodcatalog.dto.FoodItemDTO;
import com.dropp.foodcatalog.service.FoodCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodCatalog")
public class FoodCatalogController {
    private FoodCatalogService foodCatalogService;

    public FoodCatalogController(FoodCatalogService foodCatalogService){
        this.foodCatalogService = foodCatalogService;
    }

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemSaved = foodCatalogService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCatalogPage> fetchRestaurantWithFoodMenu(@PathVariable Integer restaurantId) {
        FoodCatalogPage foodCatalogPage = foodCatalogService.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCatalogPage, HttpStatus.OK);
    }

}
