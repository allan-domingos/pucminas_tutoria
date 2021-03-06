import { Routes } from '@angular/router'
import { HomeComponent } from './home/home.component';

import { PrivateComponent } from './private.component';

export const ROUTES: Routes = [
    {
        path: '', component: PrivateComponent, children: [
            { path: 'home', component: HomeComponent },
            { path: 'aquisicao', loadChildren: () => import('./aquisicao/aquisicao.module').then(m => m.AquisicaoModule) }
        ]
    }
];