import {Routes} from '@angular/router'
import { HomeComponent } from './home/home.component';
import { MapComponent } from './map/map.component';
import { PrivateComponent } from './private.component';
import { ProfileComponent } from './profile/profile.component';

export const ROUTES: Routes = [
    {path: '', component: PrivateComponent, children: [
        {path: 'home', component: HomeComponent },
        {path: 'map', component: MapComponent},
        {path: 'profile', component: ProfileComponent}
    ]}
];