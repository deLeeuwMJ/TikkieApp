import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactionsService } from '../service/transactions.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  @Input()
  result$: Observable<any>;

  constructor(private service: TransactionsService) {
    this.result$ = service.resolveItems();
  }

  ngOnInit(): void {
  }

}
