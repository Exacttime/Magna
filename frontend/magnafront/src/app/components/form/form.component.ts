import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, MatFormField, MatInput,MatLabel, MatButton],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
})
export class FormComponent {
  isLogin: boolean = false;
}
