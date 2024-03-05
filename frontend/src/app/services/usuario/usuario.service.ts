import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CrearSolicitudDeUsuario } from 'src/app/models/CrearSolicitudDeUsuario';
import { RespuestaUsuarioJwt } from 'src/app/models/RespuestaUsuarioJwt';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:8081/autenticacion'; 

  constructor(private router: Router, private http: HttpClient) {}


  
  crearUsuario(solicitud: CrearSolicitudDeUsuario): Observable<RespuestaUsuarioJwt> {
    return this.http.post<RespuestaUsuarioJwt>(`${this.apiUrl}/registro`, solicitud);
  }

  editarUsuario(id: string, usuario: any): Observable<any> { 
    return this.http.put(`${this.apiUrl}/perfil/${id}`, usuario);
  }

  editarContrasena(id: string, nuevaContrasena: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/perfil/${id}/contrasena`, { nuevaContrasena });
  }

  login(nombreUsuario: string, contrasena: string): Observable<RespuestaUsuarioJwt> {
    return this.http.post<RespuestaUsuarioJwt>(`${this.apiUrl}/login`, { nombreUsuario, contrasena });
  }
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/auth/login');
  }
}
