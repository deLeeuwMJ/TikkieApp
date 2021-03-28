import { Component, Input, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';

import { ITransaction } from '../model/transactions';
import { IPayment } from '../model/transactions';
import { IRequest } from '../model/transactions';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {
  transId: string = "-1";

  result!: ITransaction;

  @Input()
  jsonResult$!: Observable<ITransaction>;

  paymentResult$!: Observable<IPayment[]>;
  requestResult$!: Observable<IRequest[]>;

  constructor(private service: TransactionsService, private util: UtilityService) {
    this.util.transactionId$.subscribe(
      id => this.transId = id,
      err => { console.log(err) }
      );

      this.jsonResult$ = this.service.resolveItem(this.transId);
      this.jsonResult$.subscribe((data: ITransaction) => {
        this.paymentResult$ = of(data.payments);
        this.requestResult$ = of(data.requests);
      });
  }

  ngOnInit(): void {
  }

}
