import { Component, computed, inject, signal, effect, untracked } from '@angular/core';
import { FoodItemService } from '../../../../core/services/food-item.service';
import { ActivatedRoute, Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { FoodCatalogPage } from '../../models/food-catalog-page.model';
import { Restaurant } from '../../models/restaurant.model';
import { ItemCard } from '../../components/item-card/item-card';
import { FoodItem } from '../../models/food-item.model';
import { OrderSummaryDetails } from '../../models/order-summary.model';

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
  private loadedInitialQuantities = false;

  constructor() {
    effect(() => {
      const items = this.foodCatalog().items;
      if (items.length > 0 && !this.loadedInitialQuantities) {
        untracked(() => {
          const state = history.state as { data?: OrderSummaryDetails };
          if (!state.data) {
            this.quantities.update(map => {
              const newMap = new Map(map);
              items.forEach(item => {
                if (item.quantity !== undefined && item.quantity !== null) {
                  newMap.set(item.id, item.quantity);
                }
              });
              return newMap;
            });
          }
        });
        this.loadedInitialQuantities = true;
      }
    });
  }

  readonly foodCatalog = toSignal(
    this.foodItemService.getFoodItemsByRestaurantId(Number(this.restaurantId)),
    {
      initialValue: {
        items: [] as FoodItem[],
        restaurant: {} as Restaurant
      } as FoodCatalogPage
    }
  );

  readonly quantities = signal<Map<number, number>>(this.getSavedQuantities());

  private getSavedQuantities(): Map<number, number> {
    const state = history.state as { data?: OrderSummaryDetails };
    const summary = state.data;
    const map = new Map<number, number>();
    
    if (summary?.foodItemsList) {
      summary.foodItemsList.forEach((item) => {
        if (item.quantity !== undefined && item.quantity !== null) {
          map.set(item.id, item.quantity);
        }
      });
    }
    return map;
  }

  readonly cartCount = computed(() => {
    const quantities = this.quantities();
    return this.foodCatalog().items.reduce((sum, item) => {
      return sum + (quantities.get(item.id) ?? 0);
    }, 0);
  });

  readonly cartTotal = computed(() => {
    const quantities = this.quantities();
    return this.foodCatalog().items.reduce((sum, item) => {
      return sum + (item.price * (quantities.get(item.id) ?? 0));
    }, 0);
  });

  readonly orderSummary = computed<OrderSummaryDetails>(() => {
    return {
      restaurantId: this.restaurantId ?? '',
      foodItemsList: this.foodCatalog().items
        .map(item => ({
          id: item.id,
          name: item.name,
          price: item.price,
          quantity: this.quantities().get(item.id) ?? 0
        }))
        .filter(item => item.quantity > 0),
      total: this.cartTotal(),
      userId: 1,
    };
  });

  onQuantityChange(event: { id: number; quantity: number }): void {
    this.quantities.update(map => {
      const next = new Map(map);
      next.set(event.id, event.quantity);
      return next;
    });
  }

  checkout(): void {
    this.router.navigate(['/order-summary'], 
      { state: { data: this.orderSummary() } });
  }

  goBack(): void {
    this.router.navigate(['/restaurant-listing']);
  }
}
