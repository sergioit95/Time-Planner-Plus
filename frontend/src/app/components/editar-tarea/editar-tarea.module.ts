import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { EditarTareaComponent } from './editar-tarea.component';
import { EditarTareaRoutingModule } from './editar-tarea-routing.module';

@NgModule({
  declarations: [EditarTareaComponent],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EditarTareaRoutingModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class EditarTareaModule {}
