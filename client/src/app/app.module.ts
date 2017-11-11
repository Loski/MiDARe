import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA  } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';


import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { UserService } from './user/user.service';
import { AppRoutingModule } from './app-routing.module';
import { AuthComponent } from './user/auth/auth.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NavComponent } from './nav/nav.component';
import { CardComponent } from './deck/card/card.component';
import { DeckComponent } from './deck/deck.component';
import { SignupComponent } from './user/signup/signup.component';
import { DeckService } from "./deck/deck.service";
import { CardService } from "./deck/card/card.service";


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AuthComponent,
    DashboardComponent,
    UserDetailComponent,
    NavComponent,
    CardComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot()
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [
    UserService,
    DeckService,
    CardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
