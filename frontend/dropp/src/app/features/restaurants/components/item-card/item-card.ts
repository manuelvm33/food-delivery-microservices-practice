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
  @Output() quantityChange = new EventEmitter<{ id: number; quantity: number }>();

  get quantity(): number {
    return this.foodItem.quantity ?? 0;
  }

  increment(): void {
    this.foodItem = { ...this.foodItem, quantity: this.quantity + 1 };
    this.quantityChange.emit({ id: this.foodItem.id, quantity: this.foodItem.quantity });
  }

  decrement(): void {
    if (this.quantity > 0) {
      this.foodItem = { ...this.foodItem, quantity: this.quantity - 1 };
      this.quantityChange.emit({ id: this.foodItem.id, quantity: this.foodItem.quantity });
    }
  }
}
