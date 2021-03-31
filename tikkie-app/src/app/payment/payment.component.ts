import { Component, OnInit } from '@angular/core';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';
import { IPayment } from '../model/transactions';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})

export class PaymentComponent implements OnInit {

  transId = '-1';

  constructor(private service: TransactionsService, private util: UtilityService) {
    this.util.transactionId$.subscribe(
      id => this.transId = id,
      err => { console.log(err); }
      );
   }

  ngOnInit(): void {
  }

  add(): void {
    this.service.postItem();
  }

  reset(): void {
    this.service.postItem();
  }
}
