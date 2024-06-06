import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormComponent} from "../../components/form/form.component";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
}
