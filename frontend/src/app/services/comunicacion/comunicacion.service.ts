import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ComunicacionService {
  private tareaEditadaSource = new Subject<any>();
  tareaEditada$ = this.tareaEditadaSource.asObservable();

  tareaEditada(tarea: any) {
    this.tareaEditadaSource.next(tarea);
  }

  notificarTareaEditada() {
    this.tareaEditadaSource.next(null);
  }
}
