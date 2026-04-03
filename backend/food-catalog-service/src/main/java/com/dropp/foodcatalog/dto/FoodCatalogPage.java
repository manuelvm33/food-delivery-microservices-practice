package com.dropp.foodcatalog.dto;

import com.dropp.foodcatalog.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCatalogPage {
    private List<FoodItem> items;
    private Restaurant restaurant;
}
