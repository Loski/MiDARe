import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  createAccount() {
  	//this.userService.addUser(new User( '', pseudo, password, mail, zipCode, city, adress));
  }
  
  login() {
    console.log('It works here');
  }

  foo() {
    console.log('test');
  }
}
