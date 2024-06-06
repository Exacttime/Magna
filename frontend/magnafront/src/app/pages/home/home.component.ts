import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from "../../components/card/card.component";
import {CardListComponent} from "../../components/card-list/card-list.component";

@Component({
    selector: 'app-home',
    standalone: true,
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
    imports: [CommonModule, CardComponent, CardListComponent]
})
export class HomeComponent {}
