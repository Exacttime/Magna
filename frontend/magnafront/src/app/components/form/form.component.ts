import {Component, Input, OnInit} from '@angular/core';
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
export class FormComponent implements OnInit {
  @Input() mode: 'register' | 'login' = 'register';
  submit!: String;
  constructor() {
  }
  ngOnInit(): void {
    if(this.mode === "login") {
      this.submit = 'Logar';
    }
    else{
    this.submit = 'Registrar';
    }
  }
}
