import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    const url = `${this.apiUrl}/perfil/${id}`;
    return this.http.put(url, usuario, { headers: this.getHeaders() });
  }

  editarContrasena(id: string, nuevaContrasena: any): Observable<any> {
    const url = `${this.apiUrl}/perfil/${id}/contrasena`;
    return this.http.put(url, { nuevaContrasena }, { headers: this.getHeaders() });
  }

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
  }

  
  obtenerUsuarioPorId(id: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(`${this.apiUrl}/perfil/${id}`, { headers: headers });
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
