package com.portfolio.foodcatalog.service;

import com.portfolio.foodcatalog.dto.FoodCatalogPage;
import com.portfolio.foodcatalog.dto.FoodItemDTO;
import com.portfolio.foodcatalog.dto.Restaurant;
import com.portfolio.foodcatalog.entity.FoodItem;
import com.portfolio.foodcatalog.mapping.FoodItemMapper;
import com.portfolio.foodcatalog.repo.FoodItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogService {
    private final String RESTAURANT_SERVICE_URL = "https://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/";
    private FoodItemRepo foodItemRepo;

    private RestTemplate restTemplate;
    public FoodCatalogService(FoodItemRepo foodItemRepo, RestTemplate restTemplate){
        this.foodItemRepo = foodItemRepo;
        this.restTemplate = restTemplate;
    }

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO){
        FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem);
    }

    public FoodCatalogPage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItems = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurant(restaurantId);
        return createRestaurantCataloguePage(foodItems, restaurant);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId){
        return foodItemRepo.findByRestaurantId(restaurantId);
    }

    private Restaurant fetchRestaurantDetailsFromRestaurant(Integer restaurantId){
        return restTemplate.getForObject(RESTAURANT_SERVICE_URL + restaurantId, Restaurant.class);
    }

    private FoodCatalogPage createRestaurantCataloguePage(List<FoodItem> foodItems, Restaurant restaurant){
        return new FoodCatalogPage(foodItems,restaurant);
    }
}
