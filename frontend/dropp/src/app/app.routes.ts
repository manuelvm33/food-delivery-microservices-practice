import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'restaurant-listing',
        pathMatch: 'full'
    },
    {
        path: 'restaurant-listing',
        loadComponent: () =>
            import('./features/restaurants/pages/listing-restaurant/listing-restaurant')
                .then(m => m.ListingRestaurant)
    },
    {
        path: 'food-catalog/:id',
        loadComponent: () =>
            import('./features/restaurants/pages/food-catalog/food-catalog')
                .then(m => m.FoodCatalog)
    }
];
