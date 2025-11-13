package com.porflio.order.entity;

import com.porflio.order.dto.FoodItemDto;
import com.porflio.order.dto.RestaurantDto;
import com.porflio.order.dto.UserDto;
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
