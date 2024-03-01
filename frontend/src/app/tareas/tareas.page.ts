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

  completarTarea(id: string, event: MouseEvent) {
    event.stopPropagation();
    this.tareaService.completarTarea(id).subscribe({
      next: (tareaCompletada) => {
        console.log('Tarea completada:', tareaCompletada);
        // Actualiza las listas localmente aquí
        const tareaIndex = this.tareas.findIndex(tarea => tarea.id === id);
        if (tareaIndex !== -1) {
          this.tareas[tareaIndex].estaCompletada = true;
          // Añade a completadas si no está ya
          if (!this.tareasCompletadas.some(tarea => tarea.id === id)) {
            this.tareasCompletadas.push(this.tareas[tareaIndex]);
          }
          // Remueve de no completadas si está
          this.tareasNoCompletadas = this.tareasNoCompletadas.filter(tarea => tarea.id !== id);
        }
      },
      error: (error) => console.error('Error al completar la tarea', error)
    });
  }
  
  desmarcarTarea(id: string, event: MouseEvent) {
    event.stopPropagation();
    this.tareaService.desmarcarTarea(id).subscribe({
      next: (tareaDesmarcada) => {
        console.log('Tarea desmarcada:', tareaDesmarcada);
        // Actualiza las listas localmente aquí
        const tareaIndex = this.tareas.findIndex(tarea => tarea.id === id);
        if (tareaIndex !== -1) {
          this.tareas[tareaIndex].estaCompletada = false;
          // Añade a completadas si no está ya
          if (!this.tareasNoCompletadas.some(tarea => tarea.id === id)) {
            this.tareasNoCompletadas.push(this.tareas[tareaIndex]);
          }
          // Remueve de no completadas si está
          this.tareasCompletadas = this.tareasCompletadas.filter(tarea => tarea.id !== id);
        }
      },
      error: (error) => console.error('Error al desmarcar la tarea', error)
    });
  }
  
  
}
