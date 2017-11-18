import { Component, OnInit } from '@angular/core';
import {EncountersService} from './encounters.service';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {Encounter} from './encounter';

@Component({
  selector: 'app-encounters',
  templateUrl: './encounters.component.html',
  styleUrls: ['./encounters.component.scss']
})
export class EncountersComponent implements OnInit {
  encountersList:Encounter[];
  constructor(private encountersService: EncountersService) { }

  ngOnInit() {
      this.encountersService.getEncounters().subscribe(
        encounter => this.encountersList = encounter);
  }

}
