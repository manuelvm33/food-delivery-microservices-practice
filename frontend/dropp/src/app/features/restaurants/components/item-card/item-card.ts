import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FoodItem } from '../../models/food-item.model';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-item-card',
  imports: [CurrencyPipe],
  templateUrl: './item-card.html',
  styleUrl: './item-card.css',
  standalone: true
})
export class ItemCard {
  @Input() foodItem!: FoodItem;
  @Input() quantity: number = 0;
  @Output() quantityChange = new EventEmitter<{ id: number; quantity: number }>();

  increment(): void {
    this.quantityChange.emit({ id: this.foodItem.id, quantity: this.quantity + 1 });
  }

  decrement(): void {
    if (this.quantity > 0) {
      this.quantityChange.emit({ id: this.foodItem.id, quantity: this.quantity - 1 });
    }
  }
}
