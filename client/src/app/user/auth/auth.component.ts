import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  constructor(private cookieService: CookieService, private userService: UserService) { }
  user: User;

  ngOnInit() {
    this.user = new User(0, "", "", "", null, "" , "", "");
  }

  setCookie() {
    this.cookieService.set('id', "" + this.user.id);
    this.cookieService.set('token', "" + this.user.token);
    console.log(this.cookieService.get('token'));
  }

  onSubmitLogin() {
    this.userService.connexionUser(this.user).subscribe(
      user => this.user = user,
      (err) => console.error(err),
      () => this.setCookie()
    );
  }
}
