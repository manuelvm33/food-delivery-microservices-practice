import { Component, computed, inject, Signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-order-summary',
  imports: [],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css',
  standalone: true
})
export class OrderSummary {
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly queryParams = toSignal(this.route.queryParams);
  
  readonly orderSummary: Signal<any> = computed(() => {
    const dataString = this.queryParams()?.['data'];
    return dataString ? JSON.parse(dataString) : null;
  });

  placeOrder() {
    console.log('place order', this.orderSummary());
  }

  backToRestaurant() {
    const summary = this.orderSummary();
    if (summary?.restaurantId) {
      this.router.navigate(['/food-catalog', summary.restaurantId]);
    } else {
      this.router.navigate(['/restaurant-listing']);
    }
  }

}
