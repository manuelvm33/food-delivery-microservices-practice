package com.porfolio.microservices.restaurantlisting.service;

import com.porfolio.microservices.restaurantlisting.dto.RestaurantDTO;
import com.porfolio.microservices.restaurantlisting.entity.Restaurant;
import com.porfolio.microservices.restaurantlisting.repo.RestaurantRepo;
import mapper.RestaurantMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;

    public RestaurantService(RestaurantRepo restaurantRepo){
        this.restaurantRepo = restaurantRepo;
    }

    public Page<RestaurantDTO> fetchRestaurants(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return restaurantRepo.findAll(pageRequest).map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDTO);
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);
        restaurantRepo.save(restaurant);
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
    }
    public RestaurantDTO fetchRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant);
    }
}
