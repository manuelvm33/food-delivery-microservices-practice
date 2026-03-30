import { FoodItem } from './food-item.model';
import { Restaurant } from './restaurant.model';

export interface Order {
  foodItemsList: FoodItem[];
  userId: number;
  restaurant: Restaurant;
  total: number;
}
