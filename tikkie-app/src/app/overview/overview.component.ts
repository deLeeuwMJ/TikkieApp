import { Component, Input, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';

import { ITransaction } from '../model/transactions';
import { IPayment } from '../model/transactions';
import { IRequest } from '../model/transactions';
import { TransactionType } from '../model/transactiontype';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})

export class OverviewComponent implements OnInit {

  @Input()
  jsonResult$!: Observable<ITransaction>;


  transId = '-1';
  result!: ITransaction;
  paymentResult$!: Observable<IPayment[]>;
  requestResult$!: Observable<IRequest[]>;
  totalAmount!: number;

  constructor(private service: TransactionsService, private util: UtilityService) {
    this.util.transactionId$.subscribe(
      id => this.transId = id,
      err => { console.log(err); }
      );

    this.jsonResult$ = this.service.resolveItem(this.transId);
    this.jsonResult$.subscribe((data: ITransaction) => {
        this.paymentResult$ = of(data.payments);
        this.requestResult$ = of(data.requests);
        this.totalAmount = this.calculateTotalPayedAmount(data.payments);
      });
  }

  ngOnInit(): void {
  }

  generateAccordionTitle(obj: IPayment|IRequest): string {
    const type: TransactionType = 'fullName' in obj ? TransactionType.Payment : TransactionType.Request;

    switch (type) {
      case TransactionType.Payment:
        return (obj as IPayment).fullName + ' heeft €' + (obj as IPayment).amount + ' betaald';
      case TransactionType.Request:
        return (obj as IRequest).senderName + ' betaald ' + (obj as IRequest).receiverName + ' €' + (obj as IRequest).amount;
      default:
        return 'Een transactie heeft plaastgevonden.';
    }
  }

  calculateTotalPayedAmount(list: IPayment[]): number {
    let tempAmount = 0.0;

    for (const payment of list) {
      tempAmount = payment.amount + tempAmount;
    }

    return tempAmount;
  }

}
