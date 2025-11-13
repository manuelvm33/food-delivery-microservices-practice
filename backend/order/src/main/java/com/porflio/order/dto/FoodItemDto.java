package com.porflio.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean isVegan;
    private Long restaurantId;
    private Integer quantity;
}
