import { Component, OnInit, Inject, Input } from '@angular/core';
import { Observable } from "rxjs/Rx"
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../user/user.service';
import { User } from '../user/user'
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user: User;
  results: string[];
  lat: number = 51.678418;
  lng: number = 7.809007;
  
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private http: HttpClient
  ) { }


  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(user => this.infoUser(user));
      			
  }
  
  infoUser(user)
  {
  	this.user = user;
  	this.getLocation());
  }
  
  infoLocation(data):void{
    this.results = data['results'];
    this.lat = this.results[0].geometry.location.lat;
    this.lng = this.results[0].geometry.location.lng;
	}
 
	getLocation():void {
		this.http.get('https://maps.googleapis.com/maps/api/geocode/json?address='+this.user.city+'&key=AIzaSyD35gECOo2q6bowKrjlIMjgMSgMNxEUkDk').subscribe(data => this.infoLocation(data));
	}
  }

}