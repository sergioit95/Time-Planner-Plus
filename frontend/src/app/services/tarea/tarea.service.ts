import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarea } from 'src/app/models/Tarea';
import { CrearSolicitudDeTarea } from 'src/app/models/CrearSolicitudDeTarea';

@Injectable({
  providedIn: 'root'
})
export class TareaService {

  private apiUrl = 'http://localhost:8081/tareas';

  constructor(private http: HttpClient) {}



  listarTareas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(this.apiUrl);
  }

  buscarTareaPorId(id: string): Observable<Tarea> {
    return this.http.get<Tarea>(`${this.apiUrl}/${id}`);
  }

  crearTarea(solicitud: CrearSolicitudDeTarea): Observable<Tarea> {
    return this.http.post<Tarea>(this.apiUrl, solicitud);
  }

  completarTarea(id: string): Observable<Tarea> {
    return this.http.post<Tarea>(`${this.apiUrl}/${id}/completar`, {});
  }

  desmarcarTarea(id: string): Observable<Tarea> {
    return this.http.post<Tarea>(`${this.apiUrl}/${id}/desmarcar`, {});
  }

  editarTarea(id: string, tarea: Tarea): Observable<Tarea> {
    return this.http.put<Tarea>(`${this.apiUrl}/${id}`, tarea);
  }

  eliminarTareaPorId(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  obtenerTareasCompletadas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(`${this.apiUrl}/completadas`);
  }

  obtenerTareasNoCompletadas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(`${this.apiUrl}/no-completadas`);
  }
}
