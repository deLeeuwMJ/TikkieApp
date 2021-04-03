import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';
import { PaymentsService } from '../service/payments.service';
import { IPayment } from '../model/payment';
import { IRequest } from '../model/request';
import { TransactionType } from '../model/transaction';
import { RequestsService } from '../service/request.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})

export class OverviewComponent implements OnInit {

  transCode!: string;
  transDescription!: string;
  paymentResult$!: Observable<IPayment[]>;
  requestResult$!: Observable<IRequest[]>;
  totalAmount!: number;

  constructor(private paymentRepo: PaymentsService, private requestRepo: RequestsService, private transRepo: TransactionsService, private util: UtilityService) {
    this.util.transactionId$.subscribe(
      id => this.transCode = (this.transCode != null ? '-1' : id),
      error => { console.log(error); }
      );

      this.transRepo.resolveItem(this.transCode).subscribe(
        data => this.transDescription = (data.transaction_description == null ? "Geen beschrijving opgegeven." : data.transaction_description),
        error => { console.log(error); }
      );

    this.paymentRepo.resolveItem(this.transCode).subscribe(
        data => {
          this.paymentResult$ = of(data);
          this.totalAmount = this.calculateTotalPayedAmount(data);
        },
        error => { console.log(error); }
      );

      this.requestRepo.resolveItem(this.transCode).subscribe(
        data => {
          this.requestResult$ = of(data);
        },
        error => { console.log(error); }
      );
  }

  ngOnInit(): void {
  }

  generateAccordionTitle(obj: IPayment|IRequest): string {
    const type: TransactionType = 'payment_sender_name' in obj ? TransactionType.Payment : TransactionType.Request;

    switch (type) {
      case TransactionType.Payment:
        return (obj as IPayment).payment_sender_name + ' heeft €' + (obj as IPayment).payment_amount + ' betaald';
      case TransactionType.Request:
        return (obj as IRequest).request_sender_name + ' betaald ' + (obj as IRequest).request_receiver_name + ' €' + (obj as IRequest).request_amount;
      default:
        return 'Een transactie heeft plaastgevonden.';
    }
  }

  calculateTotalPayedAmount(list: IPayment[]): number {
    let tempAmount = 0.0;

    for (const payment of list) {
      tempAmount = payment.payment_amount + tempAmount;
    }

    return tempAmount;
  }

}
