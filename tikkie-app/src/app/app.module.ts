import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { OverviewComponent } from './overview/overview.component';
import { PaymentComponent } from './payment/payment.component';
import { HomeComponent } from './home/home.component';
import { TransactionsComponent } from './transactions/transactions.component'

import { FormsModule, ReactiveFormsModule} from '@angular/forms';

import { UtilityService } from './service/utility.service';
import { TransactionsService } from './service/transactions.service';

import { HttpClientModule } from '@angular/common/http'

@NgModule({
  declarations: [
    AppComponent,
    OverviewComponent,
    PaymentComponent,
    HomeComponent,
    TransactionsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [UtilityService, TransactionsService],
  bootstrap: [AppComponent]
})

export class AppModule {}
