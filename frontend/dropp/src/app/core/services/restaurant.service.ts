import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { PageResponse } from '../../shared/components/models/page-response.interface';
import { Restaurant } from '../../features/restaurants/models/restaurant.interface';

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {
  private readonly apiUrl = environment.api.restaurant + '/restaurant/fetchRestaurants';

  constructor(private http: HttpClient) { }

  getRestaurants(page: number, pageSize: number): Observable<PageResponse<Restaurant>> {
    const params = new HttpParams()
      .set('page', page)
      .set('pageSize', pageSize);
    return this.http.get<PageResponse<Restaurant>>(`${this.apiUrl}`, { params })
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
    return throwError(
      'Something bad happened; please try again later.'
    );
  }
}
