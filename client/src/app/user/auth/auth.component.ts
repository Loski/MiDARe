import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  constructor(private userService: UserService) { }
  user: User;

  ngOnInit() {
    this.user = new User(0, "", "", "", null, "" , "", "");
  }

  onSubmitLogin() {
    console.log("coucouLogin activate !");
    this.userService.connexionUser(this.user).subscribe();
  }

}
