import { Component, OnInit, Inject, Input } from '@angular/core';
import { Observable } from "rxjs/Rx"
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../user/user.service';
import { User } from '../user/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @Input() user: User;
  
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location
  ) { }


  ngOnInit(): void {
    this.getUser();
    console.log(this.user);
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
    console.log(this.user);
  }

}
