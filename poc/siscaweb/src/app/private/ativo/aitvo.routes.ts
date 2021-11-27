import {Routes} from '@angular/router'
import { AtivoComponent } from './ativo.component'
import { ListarComponent } from './listar/listar.component'
import { PatrimonioComponent } from './patrimonio/patrimonio.component'

export const ROUTES: Routes = [
    {path: '', component: AtivoComponent, children: [
        {path: '', component: ListarComponent},
        {path: 'patrimonio/:id', component: PatrimonioComponent}
    ]}
]