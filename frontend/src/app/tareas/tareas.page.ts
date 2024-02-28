import { Component, OnInit } from '@angular/core';
import { Tarea } from '../models/Tarea';
import { TareaService } from '../services/tarea/tarea.service';
import { Router } from '@angular/router';
import { ComunicacionService } from '../services/comunicacion/comunicacion.service';
@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.page.html',
  styleUrls: ['./tareas.page.scss'],
})
export class TareasPage implements OnInit {
  tareas: Tarea[] = []
  mostrarFormularioCreacion: boolean = false;

  constructor(private comunicacionService: ComunicacionService, private router: Router, private tareaService: TareaService) { }

  ngOnInit() {
    this.cargarTareas();
    this.comunicacionService.tareaEditada$.subscribe({
      next: (tareaEditada) => {
        // Aquí, puedes recargar la lista de tareas o actualizar la lista localmente
        // Por ejemplo, recargar la lista desde el servidor:
        this.cargarTareas();
      }
    });
  }

  editarTarea(id: string) {
    this.router.navigate(['/editar-tarea', id]);
  }
  cargarTareas() {
    this.tareaService.listarTareas().subscribe(tareas => {
      this.tareas = tareas;
    });
  }
  handleTareaCreada(tarea: Tarea) {
    this.tareas.push(tarea);
    this.mostrarFormularioCreacion = false; 
  }
}
