import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../../../../core/services/order.service';
import { OrderSummaryDetails } from '../../models/order-summary.model';
@Component({
  selector: 'app-order-summary',
  imports: [],
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css',
  standalone: true
})
export class OrderSummary {
  private readonly router = inject(Router);
  private readonly orderService = inject(OrderService);
  readonly showSuccessDialog = signal(false);
  
  readonly orderSummary = signal<OrderSummaryDetails | null>(
    (history.state as { data?: OrderSummaryDetails }).data ?? null
  );

  placeOrder() {
    this.orderService.createOrder(this.orderSummary()).subscribe({
      next: () => {
        this.showSuccessDialog.set(true);
      },
      error: (error) => {
        console.error('Error placing order:', error);
      }
    });
  }

  closeSuccessDialog() {
    this.showSuccessDialog.set(false);
    this.backToRestaurant();
  }

  backToRestaurant() {
    this.router.navigate(['/restaurant-listing']);
  }

  backToFoodCatalog() {
    const summary = this.orderSummary();
    if (summary) {
      this.router.navigate(['/food-catalog', summary.restaurantId], {
        state: { data: summary }
      });
    } else {
      this.router.navigate(['/restaurant-listing']);
    }
  }

}
