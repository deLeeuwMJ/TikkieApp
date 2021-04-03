import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { PaymentsService } from '../service/payments.service';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';
import { PaymentModel } from '../model/payment';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})

export class PaymentComponent implements OnInit {

  transId!: number;
  transCode!: string;
  transDescription!: string;

  nameFormControl = new FormControl('', [
    Validators.required
  ]);

  amountFormControl = new FormControl('', [
    Validators.required
  ]);

  descriptionFormControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private router: Router, private service: PaymentsService, private transRepo: TransactionsService, private util: UtilityService) {
    this.util.transactionId$.subscribe(
      id => this.transCode = id,
      err => { console.log(err); }
      );

    this.transRepo.resolveItem(this.transCode).subscribe(
        transactionResult => this.transId = transactionResult.transaction_id,
        err => { console.log(err); }
      );
   }

  ngOnInit(): void {
  }

  add(): void {
    this.service.postItem(
      new PaymentModel(
        -1,
        this.transId,
        this.nameFormControl.value,
        formatDate(new Date(), 'yyyy/MM/dd', 'en'),
        this.descriptionFormControl.value,
        this.amountFormControl.value
        )
      ).subscribe();

    this.router.navigate([this.transCode, 'overview']);
  }

  reset(): void {
    this.service.reset(this.transCode).subscribe();
    this.router.navigate(["home"]);
  }
}
