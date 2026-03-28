package com.portfolio.foodcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean vegan;
    private Long restaurantId;
    private Integer quantity;
}
