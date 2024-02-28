import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioService } from '../usuario/usuario.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private usuarioService: UsuarioService) {} // Asume que tienes un servicio de autenticación

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Obtén el token de autenticación de alguna manera (por ejemplo, del localStorage o de tu AuthService)
    const authToken = this.usuarioService.getToken(); // Asume que tu AuthService tiene un método getToken()

    // Clona la solicitud original y añade la cabecera de autorización con el token
    const authReq = request.clone({
      headers: request.headers.set('Authorization', 'Bearer ' + authToken)
    });

    return next.handle(authReq);
  }
}
