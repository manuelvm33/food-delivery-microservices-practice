package com.porfolio.microservices.restaurantlisting.repo;

import com.porfolio.microservices.restaurantlisting.dto.RestaurantDTO;
import com.porfolio.microservices.restaurantlisting.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();

}
