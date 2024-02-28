import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { CrearTareaFormComponent } from './crear-tarea-form.component';

@NgModule({
  declarations: [CrearTareaFormComponent],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule 
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [CrearTareaFormComponent]
})
export class CrearTareaFormModule {}
