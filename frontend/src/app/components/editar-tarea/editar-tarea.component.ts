import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../models/Tarea';
import { TareaService } from '../../services/tarea/tarea.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ComunicacionService } from '../../services/comunicacion/comunicacion.service';
import { NavController } from '@ionic/angular';
@Component({
  selector: 'app-editar-tarea',
  templateUrl: './editar-tarea.component.html',
  styleUrls: ['./editar-tarea.component.scss'],
})
export class EditarTareaComponent implements OnInit {
  tarea: Tarea = new Tarea(); 
  id!: string;

  constructor(private comunicacionService: ComunicacionService, private route: ActivatedRoute, private tareaService: TareaService, private router: Router) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id') ?? '';
    if (this.id) {
      this.cargarDatosDeTarea();
    }
  }
  
  
  cargarDatosDeTarea() {
    this.tareaService.buscarTareaPorId(this.id).subscribe({
      next: (tarea) => {
        this.tarea = tarea;
      },
      error: (error) => {
        console.error('Error al cargar la tarea', error);
      },
    });
  }
  
  editarTarea() {
    this.tareaService.editarTarea(this.id, this.tarea).subscribe({
      next: (tareaEditada) => {
        console.log('Tarea editada con éxito', tareaEditada);
        this.comunicacionService.tareaEditada(tareaEditada);
        this.router.navigate(['/main/tareas']);
      },
      error: (error) => {
        console.error('Error al editar la tarea', error);
      },
    });
  }
  cancelarEdicion() {
    this.router.navigate(['/main/tareas']);
  }

  eliminarTarea() {
    this.tareaService.eliminarTareaPorId(this.id).subscribe({
      next: () => {
        console.log('Tarea eliminada con éxito');
        this.router.navigate(['/main/tareas']); 
      },
      error: (error) => {
        console.error('Error al eliminar la tarea', error);
      }
    });
  }
  
  
}
