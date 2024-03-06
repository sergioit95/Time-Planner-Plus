import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioService } from '../usuario/usuario.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private usuarioService: UsuarioService) {} 
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.usuarioService.getToken(); 

    // Clona la solicitud original y añade la cabecera de autorización con el token
    const authReq = request.clone({
      headers: request.headers.set('Authorization', 'Bearer ' + authToken)
    });

    return next.handle(authReq);
  }
}
