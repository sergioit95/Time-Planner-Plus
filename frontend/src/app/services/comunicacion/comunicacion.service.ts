import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ComunicacionService {
  private tareaEditadaSource = new Subject<any>();
  tareaEditada$ = this.tareaEditadaSource.asObservable();

  private tareaCreadaSource = new Subject<any>();
  tareaCreada$ = this.tareaCreadaSource.asObservable();

  tareaEditada(tarea: any) {
    this.tareaEditadaSource.next(tarea);
  }

  tareaCreada(tarea: any) {
    this.tareaCreadaSource.next(tarea);
  }

  
}
