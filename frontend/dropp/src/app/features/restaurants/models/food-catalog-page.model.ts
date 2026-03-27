import { FoodItem } from "./food-item.model";
import { Restaurant } from "./restaurant.model";

export interface FoodCatalogPage {
    items: FoodItem[];
    restaurant: Restaurant;
}