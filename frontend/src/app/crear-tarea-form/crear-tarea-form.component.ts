import { Component, EventEmitter, Output } from '@angular/core';
import { TareaService } from '../services/tarea/tarea.service';
import { CrearSolicitudDeTarea } from '../models/CrearSolicitudDeTarea';
import { Router } from '@angular/router';
@Component({
  selector: 'app-crear-tarea-form',
  templateUrl: './crear-tarea-form.component.html',
  styleUrls: ['./crear-tarea-form.component.scss'],
})
export class CrearTareaFormComponent {
  @Output() tareaCreada: EventEmitter<any> = new EventEmitter(); // Ajusta según tu modelo de Tarea

  titulo: string = '';
  descripcion: string = '';
  estaCompletada!: boolean;

  constructor(private router: Router, private tareaService: TareaService) {}

  crearTarea() {
    const nuevaTarea: CrearSolicitudDeTarea = {
      titulo: this.titulo,
      descripcion: this.descripcion,
      estaCompletada: this.estaCompletada
    };

    this.tareaService.crearTarea(nuevaTarea).subscribe({
      next: (tarea) => {
        console.log('Tarea creada con éxito');
        this.tareaCreada.emit(tarea);
        this.router.navigate(['/tareas']);
      },
      error: (error) => {
        console.error('Error al crear la tarea', error);
      },
    });
  }
  cancelarCreacion() {
    this.router.navigate(['/tareas']);
  }
}
