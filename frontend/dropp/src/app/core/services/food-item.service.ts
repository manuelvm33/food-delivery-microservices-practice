import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class FoodItemService {
  private readonly baseUrl = environment.api.foodCatalog + '/foodCatalog';

  private readonly http = inject(HttpClient);

  getFoodItemsByRestaurantId(restaurantId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/fetchRestaurantAndFoodItemById/${restaurantId}`)
    .pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status},
        body was: ${error.error}`
      );
    }
    return throwError(() => 'Something bad happened; please try again later.');
  }
}
