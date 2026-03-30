import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private readonly baseUrl = environment.api.order + '/order';
  private readonly http = inject(HttpClient);

  private readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  createOrder(order: any) {
    return this.http.post(`${this.baseUrl}/saveOrder`, order, this.httpOptions);
  }
}
