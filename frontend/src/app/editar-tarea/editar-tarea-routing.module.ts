import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditarTareaComponent } from './editar-tarea.component';

const routes: Routes = [
  {
    path: '',
    component: EditarTareaComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EditarTareaRoutingModule {}
