import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TareasPageRoutingModule } from './tareas-routing.module';

import { TareasPage } from './tareas.page';
import { CrearTareaFormModule } from '../crear-tarea-form/crear-tarea-form.module';
import { EditarTareaModule } from '../editar-tarea/editar-tarea.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    CrearTareaFormModule,
    EditarTareaModule,
    TareasPageRoutingModule
  ],
  declarations: [TareasPage]
})
export class TareasPageModule {}
