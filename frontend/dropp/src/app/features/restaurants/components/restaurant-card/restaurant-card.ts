import { Component, Input, inject } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-card',
  imports: [],
  templateUrl: './restaurant-card.html',
  styleUrl: './restaurant-card.css',
})
export class RestaurantCard {
  private router = inject(Router);
  @Input() restaurant!: Restaurant;

  order(id: number) {
    this.router.navigate(['/food-cataloge', id]);
  }
}
