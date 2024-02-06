import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from 'src/app/models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {
  private apiUrl = 'http://localhost:8080/administrador';

  constructor(private http: HttpClient) { 
    
  }
  crearUsuario(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(`${this.apiUrl}/usuarios`, usuario);
  }

  eliminarUsuario(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}usuarios/${id}`)
  }
}

