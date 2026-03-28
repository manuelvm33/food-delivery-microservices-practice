import { Component, computed, inject, signal } from '@angular/core';
import { FoodItemService } from '../../../../core/services/food-item.service';
import { ActivatedRoute, Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { FoodCatalogPage } from '../../models/food-catalog-page.model';
import { Restaurant } from '../../models/restaurant.model';
import { ItemCard } from '../../components/item-card/item-card';

@Component({
  selector: 'app-food-catalog',
  imports: [ItemCard],
  templateUrl: './food-catalog.html',
  styleUrl: './food-catalog.css',
  standalone: true
})
export class FoodCatalog {
  private readonly foodItemService = inject(FoodItemService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly restaurantId = this.route.snapshot.paramMap.get('id');

  readonly foodCatalog = toSignal(
    this.foodItemService.getFoodItemsByRestaurantId(Number(this.restaurantId)),
    {
      initialValue: {
        items: [],
        restaurant: {} as Restaurant
      } as FoodCatalogPage
    }
  );

  private readonly quantities = signal<Map<number, number>>(new Map());

  readonly cartCount = computed(() =>
    Array.from(this.quantities().values()).reduce((sum, q) => sum + q, 0)
  );

  readonly cartTotal = computed(() => {
    const quantities = this.quantities();
    return this.foodCatalog().items.reduce((sum, item) => {
      return sum + (item.price * (quantities.get(item.id) ?? 0));
    }, 0);
  });

  readonly orderSummary = computed(() => {
    return {
      restaurantId: this.restaurantId,
      items: Array.from(this.quantities().entries()).map(([id, quantity]) => ({
        id,
        quantity
      })),
      total: this.cartTotal()
    };
  });

  onQuantityChange(event: { id: number; quantity: number }): void {
    this.quantities.update(map => {
      const next = new Map(map);
      if (event.quantity === 0) {
        next.delete(event.id);
      } else {
        next.set(event.id, event.quantity);
      }
      return next;
    });
  }

  checkout(): void {
    this.router.navigate(['/order-summary'], { queryParams: { data: JSON.stringify(this.orderSummary()) } });

  }
}
