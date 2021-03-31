import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UtilityService } from '../service/utility.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  transId!: string;
  transactionFormControl = new FormControl('', [
    Validators.required
  ]);

  constructor(private router: Router, private util: UtilityService) { }

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
}
