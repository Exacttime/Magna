import { Route } from '@angular/router';

export const appRoutes: Route[] = [
    {
        path:'home',
        loadChildren: () => import('./pages/home/home.component').then(m => m.HomeComponent)
    },
    {

    }


];
