import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ITransaction } from '../model/transactions';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {
  private readonly URL = 'http://tikkie.mocklab.io/v1/transactions';

  constructor(private http: HttpClient) { }

  resolveItems(): Observable<any> {
    console.log('ALL Get request is sent!');
    return this.http.get(this.URL);
  }

  resolveItem(id: string): Observable<ITransaction> {
    console.log('INDIVIDUAL Get request is sent!');
    return this.http.get<ITransaction>(this.URL + '/' + id);
  }

  postItem(): Observable<any> {
    console.log('NEW Post request is sent!');
    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'})
    };
    return this.http.post(this.URL,
    {
      email : 'ankit.codechintan@gmail.com'
    },
    headers);
  }

  reset(): Observable<any> {
    console.log('RESET Post request is sent!');
    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'})
    };
    return this.http.post(this.URL,
    {
      email : 'ankit.codechintan@gmail.com'
    },
    headers);
  }
}
