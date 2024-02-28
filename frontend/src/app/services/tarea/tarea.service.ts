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

  private obtenerHttpOptions(): { headers: HttpHeaders } {
    const token = localStorage.getItem('token'); 
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
  }

  listarTareas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(this.apiUrl, this.obtenerHttpOptions());
  }

  buscarTareaPorId(id: string): Observable<Tarea> {
    return this.http.get<Tarea>(`${this.apiUrl}/${id}`, this.obtenerHttpOptions());
  }

  crearTarea(solicitud: CrearSolicitudDeTarea): Observable<Tarea> {
    return this.http.post<Tarea>(this.apiUrl, solicitud, this.obtenerHttpOptions());
  }

  completarTarea(id: string): Observable<Tarea> {
    return this.http.post<Tarea>(`${this.apiUrl}/${id}/completar`, {}, this.obtenerHttpOptions());
  }

  desmarcarTarea(id: string): Observable<Tarea> {
    return this.http.post<Tarea>(`${this.apiUrl}/${id}/desmarcar`, {}, this.obtenerHttpOptions());
  }

  editarTarea(id: string, tarea: Tarea): Observable<Tarea> {
    return this.http.put<Tarea>(`${this.apiUrl}/${id}`, tarea, this.obtenerHttpOptions());
  }

  eliminarTareaPorId(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, this.obtenerHttpOptions());
  }

  obtenerTareasCompletadas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(`${this.apiUrl}/completadas`, this.obtenerHttpOptions());
  }

  obtenerTareasNoCompletadas(): Observable<Tarea[]> {
    return this.http.get<Tarea[]>(`${this.apiUrl}/no-completadas`, this.obtenerHttpOptions());
  }
}
