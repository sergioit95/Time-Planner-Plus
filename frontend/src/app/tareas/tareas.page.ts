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
  tareas: Tarea[] = [];
  tareasCompletadas: Tarea[] = [];
  tareasNoCompletadas: Tarea[] = [];
  mostrarFormularioCreacion: boolean = false;
  tabActivo: string = 'todas';


  constructor(private comunicacionService: ComunicacionService, private router: Router, private tareaService: TareaService) { }

  ngOnInit() {
    this.cargarTareas();
    this.cargarTareasCompletadas();
    this.cargarTareasNoCompletadas();
    this.comunicacionService.tareaEditada$.subscribe({
      next: (tareaEditada) => {
        this.cargarTareas();
        this.cargarTareasCompletadas();
        this.cargarTareasNoCompletadas();
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

  cargarTareasCompletadas() {
    this.tareaService.obtenerTareasCompletadas().subscribe(tareas =>{
      this.tareasCompletadas = tareas;
    });
  }

  cargarTareasNoCompletadas() {
    this.tareaService.obtenerTareasNoCompletadas().subscribe(tareas =>{
      this.tareasNoCompletadas = tareas;
    })
  }
  handleTareaCreada(tarea: Tarea) {
    this.tareas.push(tarea);
    this.mostrarFormularioCreacion = false; 
  }

  cambiarTab(tab: string) {
    this.tabActivo = tab;
  }
}
