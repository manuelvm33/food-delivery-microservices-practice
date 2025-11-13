package com.portfolio.foodcatalogue.service;

import com.portfolio.foodcatalogue.dto.FoodCataloguePage;
import com.portfolio.foodcatalogue.dto.FoodItemDTO;
import com.portfolio.foodcatalogue.dto.Restaurant;
import com.portfolio.foodcatalogue.entity.FoodItem;
import com.portfolio.foodcatalogue.mapping.FoodItemMapper;
import com.portfolio.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO){
        FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItems = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurant(restaurantId);
        return createRestaurantCataloguePage(foodItems, restaurant);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId){
        return foodItemRepo.findByRestaurantId(restaurantId);
    }

    private Restaurant fetchRestaurantDetailsFromRestaurant(Integer restaurantId){
        return restTemplate.getForObject("https://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/" + restaurantId, Restaurant.class);
    }

    private FoodCataloguePage createRestaurantCataloguePage(List<FoodItem> foodItems, Restaurant restaurant){
        return new FoodCataloguePage(foodItems,restaurant);
    }
}
