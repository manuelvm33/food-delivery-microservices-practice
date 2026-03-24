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
            import('./components/listing-restaurant/listing-restaurant')
        .then(m => m.ListingRestaurant)
    }
];
