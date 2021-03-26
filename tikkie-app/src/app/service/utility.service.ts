import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {

  transactionId: BehaviorSubject<string> = new BehaviorSubject("");
  transactionId$ = this.transactionId.asObservable();

  setTransactionId(id: string) {
    this.transactionId.next(id);
  }
}
