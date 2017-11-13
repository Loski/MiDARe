import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  constructor(private cookieService: CookieService, private userService: UserService) { }
  user: User;

  ngOnInit() {
    this.user = new User(0, "", "", "", null, "", "", null, "");
  }

  setCookie() {
    this.cookieService.set('id', "" + this.user.id);
    this.cookieService.set('token', "" + this.user.token);
    console.log(this.cookieService.get('token'));
  }
  
  onSubmitCreateAccount() {
    this.userService.addUser(this.user).subscribe(
      user => this.user = user,
      (err) => console.error(err),
      () => this.setCookie()
    );
  }
}
