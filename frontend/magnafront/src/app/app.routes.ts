import { Route } from '@angular/router';

export const appRoutes: Route[] = [
    {
        path:'',
        loadComponent: () => import('./pages/home/home.component').then(m => m.HomeComponent),
    },
    {
        path:'register',
        loadComponent: () => import('./pages/register/register.component').then(m => m.RegisterComponent),
    },
    {
        path:'login',
        loadComponent:() => import('./pages/login/login-page.component').then(m => m.LoginPageComponent)
    }

];
