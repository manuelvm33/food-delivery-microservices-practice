import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { Restaurant } from '../../models/restaurant.interface';
import { Router } from '@angular/router';
import { toObservable, toSignal } from '@angular/core/rxjs-interop';
import { finalize, switchMap } from 'rxjs';
import { RestaurantService } from '../../../../core/services/restaurant.service';
import { RestaurantCard } from "../../components/restaurant-card/restaurant-card";
import { PageResponse } from '../../../../shared/components/models/page-response.interface';
import { Pagination } from '../../../../shared/components/pagination/pagination';

@Component({
  selector: 'app-listing-restaurant',
  imports: [RestaurantCard, Pagination],
  templateUrl: './listing-restaurant.html',
  styleUrl: './listing-restaurant.css',
})
export class ListingRestaurant {
  readonly page = signal<number>(0);
  readonly pageSize = signal<number>(10);

  private readonly queryParams = computed(() => ({
    page: this.page(),
    pageSize: this.pageSize(),
  }));

  private readonly restaurantService = inject(RestaurantService);
  readonly loading = signal<boolean>(true);
  readonly listRestaurants = toSignal(
    toObservable(this.queryParams).pipe(
      switchMap(({page, pageSize}) => {
        return this.restaurantService.getRestaurants(page, pageSize).pipe(
          finalize(() => this.loading.set(false))
        )
      })
    ), 
    {
      initialValue: 
      {
        content: [],
        totalPages: 0,
        totalElements: 0,
        size: 0,
        number: 0
      } as PageResponse<Restaurant>
    });

  onPageChange($event: number) {
    this.page.set($event);
  }

  onPageSizeChange($event: number) {
    this.pageSize.set($event);
  }

}
