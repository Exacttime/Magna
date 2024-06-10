import {Component, Input, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {AuthenticationService} from "../../core/services/authentication.service";
import {RegisterDTO} from "../../shared/models/registerDTO";
import {FormsModule} from "@angular/forms";
import {LoginDTO} from "../../shared/models/loginDTO";
import {Router} from "@angular/router";

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [CommonModule, MatFormField, MatInput, MatLabel, MatButton, FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.scss',
})
export class FormComponent implements OnInit {
  @Input() mode: 'register' | 'login' = 'register';

  submit!: string;
  password!: string;
  username!: string;
  email!: string;
  registerUser: RegisterDTO = { username: '', password: '', email: '', roles:["ROLE_USER"] }; // Initialize registerUser
  loginUser: LoginDTO = { username: '', password: ''}; // Initialize registerUser
  constructor(private authentication: AuthenticationService, private router: Router) {
  }
  ngOnInit(): void {
    if(!this.isRegister()) {
      this.submit = 'Logar';
    }
    else{
      this.submit = 'Registrar';
    }
  }
  submitHandler() {
    if (this.isRegister()) {
      this.handleRegister(this.registerUser);
      this.authentication.registerUser(this.registerUser).subscribe(
          (user) => {
            console.log('User registered:', user);
            // Após o registro bem-sucedido, chamar a função de login
            this.authentication.loginUser(this.registerUser).subscribe(
                (loginResponse) => {
                  this.handleLoginSuccess(loginResponse);
                },
                (error) => {
                  console.error('Login after registration failed', error);
                }
            );
          },
          (error) => {
            console.error('Registration failed', error);
          }
      );
    } else {
      this.handleLogin(this.loginUser);
      this.authentication.loginUser(this.loginUser).subscribe(
          (loginResponse) => {
            this.handleLoginSuccess(loginResponse);
          },
          (error) => {
            console.error('Login failed', error);
          }
      );
    }
  }
  handleLoginSuccess(loginResponse: any) {
    this.authentication.isAuthenticated = true;
    // Supondo que o loginResponse contenha o token e o userId
    this.authentication.storeToken(loginResponse.token);
    this.authentication.storeUserId(loginResponse.userId);
    console.log('Login successful', loginResponse);
    this.router.navigate(['/home'])
  }
  isRegister(): boolean{
    return this.mode !== "login";
  }
  handleRegister(registerUser: RegisterDTO) {
  registerUser.username = this.username;
  registerUser.password = this.password;
  registerUser.email = this.email;
  }
  handleLogin(loginUser: LoginDTO) {
    loginUser.username = this.username;
    loginUser.password = this.password;
  }
}
