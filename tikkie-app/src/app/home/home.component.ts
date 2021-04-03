import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TransactionsService } from '../service/transactions.service';
import { UtilityService } from '../service/utility.service';
import { TransactionModel } from '../model/transaction';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  disabled = false;
  transId!: string;

  transactionFormControl = new FormControl('', [
    Validators.required
  ]);

  descriptionFormControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private router: Router, private util: UtilityService, private transactionService: TransactionsService) { }

  ngOnInit(): void {
    this.util.transactionId$.subscribe(id => {
      this.transId = id;
    });
  }

  lookup(): void {
    console.log('Typed in ID', this.transactionFormControl.value);
    this.util.setTransactionId(this.transactionFormControl.value);

    this.router.navigate([this.transId, 'overview']);
  }

  create(): void {
    console.log('Typed in ID', this.transactionFormControl.value);
    this.util.setTransactionId(this.transactionFormControl.value);

    this.transactionService.postItem(
      new TransactionModel(
        -1,
        this.transactionFormControl.value,
        this.descriptionFormControl.value
        )
      ).subscribe();

      /* make check to verifiy code */

    this.router.navigate([this.transId, 'overview']);
  }
}
