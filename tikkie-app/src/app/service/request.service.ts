import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IRequest } from '../model/request';

@Injectable({
  providedIn: 'root'
})
export class RequestsService {
  private readonly URL = 'http://localhost:8080/requests';

  constructor(private http: HttpClient) { }

  postItem(request: IRequest): Observable<any> {
    console.log(request.request_id);

    const headers =  {
      headers: new  HttpHeaders({
        'Content-Type': 'application/json'})
    };
    return this.http.post(this.URL + '/create',{
      transaction_id: request.transaction_id,
      request_sender_name: request.request_sender_name,
      request_receiver_name: request.request_receiver_name,
      request_creation_date: request.request_creation_date,
      request_amount: request.request_amount,
    },
    headers);
  }

  resolveItems(): Observable<any> {
    console.log('ALL Get request is sent!');
    return this.http.get(this.URL);
  }

  resolveItem(id: string): Observable<IRequest[]> {
    console.log('INDIVIDUAL Get request is sent!');
    return this.http.get<IRequest[]>(this.URL + '/' + id);
  }
}
