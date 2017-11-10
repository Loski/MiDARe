import { Component } from '@angular/core';
import { User } from '../user/user';
import { userService } from '../user/user.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  users: User[];
  constructor(private userService: UserService) { }

  ngOnInit() {
  }
	
  message = " ";

  createAccount() {
    console.log('It works here');
  	/*this.userService.addUser(new User( '', pseudo, password, mail, zipCode, city, adress);*/
  	this.users = userService.getUsers().subscribe(users => this.users = users);
  }

}
