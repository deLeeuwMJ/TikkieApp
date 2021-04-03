import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IPayment } from '../model/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {
  private readonly URL = 'http://localhost:8080/payments';

  constructor(private http: HttpClient) { }

  postItem(payment: IPayment): Observable<any> {
    console.log(payment.payment_id);

    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/json'})
    };
    return this.http.post(this.URL + '/create',{
      transaction_id : payment.transaction_id,
      payment_sender_name : payment.payment_sender_name,
      payment_creation_date : payment.payment_creation_date,
      payment_description : payment.payment_description,
      payment_amount : payment.payment_amount
    },
    headers);
  }

  resolveItems(): Observable<any> {
    console.log('ALL Get request is sent!');
    return this.http.get(this.URL);
  }

  resolveItem(id: string): Observable<IPayment[]> {
    console.log('INDIVIDUAL Get request is sent!');
    return this.http.get<IPayment[]>(this.URL + '/' + id);
  }

  reset(code: String): Observable<any> {
    console.log(code);

    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'})
    };
    return this.http.post(this.URL + '/reset', code, headers);
  }
}
