import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { DeckService } from "./deck.service";
import { Deck } from "./deck";

@Component({
  selector: 'app-deck',
  templateUrl: './deck.component.html',
  styleUrls: ['./deck.component.scss']
})
export class DeckComponent implements OnInit {

  @Input() deck: Deck;
  constructor(private deckService: DeckService) { }

  ngOnInit(): void {
  }

}
