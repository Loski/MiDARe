import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService) { }
  user: User;

  ngOnInit() {
    this.user = new User(0, "", "", "", 0, "", "");
  }

  onSubmitCreateAccount() {
    this.userService.addUser(this.user).subscribe();
  }
}
