import {inject} from '@angular/core';
import { Router, CanActivateFn} from '@angular/router';
import {AuthenticationService} from "../core/services/authentication.service";

export const authenticationGuard: CanActivateFn = (route,state) => {
    const authService = inject(AuthenticationService);
    const router = inject(Router);
    if(authService.isAuthenticated){
        console.log("Acess Granted");
        return true;
    }else{
        router.navigate(['/login'])
        return false;
    }
}