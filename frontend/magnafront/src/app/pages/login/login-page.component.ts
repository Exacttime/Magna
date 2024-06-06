import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormComponent} from "../../components/form/form.component";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [CommonModule, FormComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss',
})
export class LoginPageComponent {}
