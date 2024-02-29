import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { CrearTareaFormComponent } from './crear-tarea-form/crear-tarea-form.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: '',
    loadChildren: () => import('./registro/registro.module').then(m => m.RegistroPageModule)
  },
  {
    path: '',
    loadChildren: () => import('./tareas/tareas.module').then(m => m.TareasPageModule)
  },
  {
    path: 'editar-tarea/:id',
    loadChildren: () => import('./editar-tarea/editar-tarea.module').then(m => m.EditarTareaModule)
  },
  {
    path: 'tareas/crear-tarea',
    component: CrearTareaFormComponent
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
