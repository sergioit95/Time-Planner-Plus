import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TareasPageRoutingModule } from './tareas-routing.module';

import { TareasPage } from './tareas.page';
import { CrearTareaFormModule } from '../../components/crear-tarea-form/crear-tarea-form.module';
import { EditarTareaModule } from '../../components/editar-tarea/editar-tarea.module';
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    TareasPageRoutingModule,
    CrearTareaFormModule,
    EditarTareaModule
  ],
  declarations: [TareasPage]
})
export class TareasPageModule {}
