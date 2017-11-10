import { Component, OnInit } from '@angular/core';
import { User }    from './user';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  
  onSubmit() {
  	this.userService.addUser({ '', pseudo, password, mail, zipCode, city, adress} as User);
  }

}
