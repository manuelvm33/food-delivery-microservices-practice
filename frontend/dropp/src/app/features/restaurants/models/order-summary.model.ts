import { OrderItem } from "./order-item.model";

export interface OrderSummaryDetails {
  restaurantId: number | string;
  foodItemsList: OrderItem[];
  total: number;
  userId: number;
}
