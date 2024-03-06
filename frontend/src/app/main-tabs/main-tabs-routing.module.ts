import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainTabsPage } from './main-tabs.page';
import { CrearTareaFormComponent } from '../crear-tarea-form/crear-tarea-form.component';
import { LogoutComponent } from '../logout/logout.component';

const routes: Routes = [
  {
    path: '',
    component: MainTabsPage,
    children: [
      {
        path: 'tareas',
        loadChildren: () => import('../tareas/tareas.module').then(m => m.TareasPageModule)
      },
      {
        path: 'editar-tarea/:id',
        loadChildren: () => import('../editar-tarea/editar-tarea.module').then(m => m.EditarTareaModule)
      },
      {
        path: 'tareas/crear-tarea',
        component: CrearTareaFormComponent
      },
      { 
        path: 'logout', 
        component: LogoutComponent
      },
      { 
        path: 'perfil', 
        loadChildren: () => import('../perfil/perfil.module').then(m => m.PerfilModule)
      }
 
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainTabsPageRoutingModule {}
