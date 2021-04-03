import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITransaction } from '../model/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private readonly URL = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) { }

  postItem(transaction: ITransaction): Observable<any> {
    console.log(transaction.transaction_code);

    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/json'})
    };
    return this.http.post(this.URL + '/create',{
      transaction_code : transaction.transaction_code,
      transaction_description : transaction.transaction_description
    },
    headers);
  }

  resolveItems(): Observable<any> {
    console.log('ALL Get request is sent!');
    return this.http.get(this.URL);
  }

  resolveItem(id: string): Observable<ITransaction> {
    console.log('INDIVIDUAL Get request is sent!');
    return this.http.get<ITransaction>(this.URL + '/' + id);
  }
}
