import { Component, EventEmitter, Output } from '@angular/core';
import { TareaService } from '../services/tarea/tarea.service';
import { CrearSolicitudDeTarea } from '../models/CrearSolicitudDeTarea';

@Component({
  selector: 'app-crear-tarea-form',
  templateUrl: './crear-tarea-form.component.html',
  styleUrls: ['./crear-tarea-form.component.scss'],
})
export class CrearTareaFormComponent {
  @Output() tareaCreada: EventEmitter<any> = new EventEmitter(); // Ajusta según tu modelo de Tarea

  titulo: string = '';
  descripcion: string = '';

  constructor(private tareaService: TareaService) {}

  crearTarea() {
    const nuevaTarea: CrearSolicitudDeTarea = {
      titulo: this.titulo,
      descripcion: this.descripcion,
    };

    this.tareaService.crearTarea(nuevaTarea).subscribe({
      next: (tarea) => {
        console.log('Tarea creada con éxito');
        this.tareaCreada.emit(tarea);
      },
      error: (error) => {
        console.error('Error al crear la tarea', error);
      },
    });
  }
}
