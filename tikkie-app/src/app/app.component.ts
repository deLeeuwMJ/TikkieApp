import { Component, OnInit } from '@angular/core';
import { UtilityService } from './service/utility.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit{
  title = 'tikkie-app';
  id!: string;

  constructor(private util: UtilityService){}

  ngOnInit(): void {
    this.util.transactionId$.subscribe(id => {
      this.id = id;
    });
    this.util.setTransactionId(window.location.pathname.replace(/^\/([^/]*).*$/, '$1'));
  }
}
