package com.portfolio.foodcatalog.mapping;

import com.portfolio.foodcatalog.dto.FoodItemDTO;
import com.portfolio.foodcatalog.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
    FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
    FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);

}
