import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatFormField, FormsModule, MatLabel, MatInput],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent {
  @Input() title = 'Tate no yuusha';
  @Input() content = 'Anime do escudo';
  @Input() imageUrl = 'https://material.angular.io/assets/img/examples/shiba2.jpg';
  @Input() currentChapter: number = 1;
}
