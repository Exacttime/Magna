import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manga } from 'src/app/shared/models/mangaDTO';
import {RegisterDTO} from 'src/app/shared/models/registerDTO';
import {UserDTO} from "../../shared/models/userDTO";
import {LoginDTO} from "../../shared/models/loginDTO";
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    private apiUrl = 'http://localhost:8080/api/auth';
    public isAuthenticated: boolean = false;

    constructor(private http: HttpClient, private router: Router) {}
    registerUser(registerUser: RegisterDTO): Observable<UserDTO> {
        return this.http.post<UserDTO>(`${this.apiUrl}/signup`, registerUser);
    }
    loginUser(loginUser: LoginDTO){
        return this.http.post<UserDTO>(`${this.apiUrl}/login`, loginUser);
    }
    isLoggedIn(): boolean {
        return this.isAuthenticated;
    }
    logout() {
        this.isAuthenticated = false;
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        this.router.navigate(['/login']);
    }
    storeToken(token: string) {
        localStorage.setItem('token', token);
    }

    storeUserId(userId: string) {
        localStorage.setItem('userId', userId);
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    getUserId(): string | null {
        return localStorage.getItem('userId');
    }
}