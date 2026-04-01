package com.drop.order.entity;

import com.drop.order.dto.RestaurantDto;
import com.drop.order.dto.FoodItemDto;
import com.drop.order.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {
    private Long id;
    private List<FoodItemDto> foodItemsList;
    private RestaurantDto restaurant;
    private UserDto userDto;
}
