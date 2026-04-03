package com.dropp.restaurant.controlller;

import com.dropp.restaurant.dto.RestaurantDTO;
import com.dropp.restaurant.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @GetMapping("fetchRestaurants")
    public ResponseEntity<Page<RestaurantDTO>> fetchAllRestaurants(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<RestaurantDTO> allRestaurants = restaurantService.fetchRestaurants(page, size);
        return ResponseEntity.ok(allRestaurants);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO savedRestaurant = restaurantService.addRestaurant(restaurantDTO);
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/fetchRestaurantById/{id}")
    public ResponseEntity<RestaurantDTO> fetchRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurantDTO = restaurantService.fetchRestaurantById(id);
        return ResponseEntity.ok(restaurantDTO);
    }
}