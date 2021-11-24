import { Routes } from '@angular/router'
import { PrivateGuard } from './private/private.guard';

export const ROUTES: Routes = [
    {path: '', loadChildren : ()=> import('./private/private.module').then(m => m.PrivateModule)
        , canLoad: [PrivateGuard], canActivate: [PrivateGuard]
    }
];