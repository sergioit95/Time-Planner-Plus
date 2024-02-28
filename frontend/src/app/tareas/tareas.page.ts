import { Component, OnInit } from '@angular/core';
import { Tarea } from '../models/Tarea';
import { TareaService } from '../services/tarea/tarea.service';
@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.page.html',
  styleUrls: ['./tareas.page.scss'],
})
export class TareasPage implements OnInit {
  tareas: Tarea[] = []
  mostrarFormularioCreacion: boolean = false;
  constructor(private tareaService: TareaService) { }

  ngOnInit() {
    this.cargarTareas();
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
