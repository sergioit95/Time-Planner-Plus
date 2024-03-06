import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../services/usuario/usuario.service';
import { Router } from '@angular/router';
import { RespuestaLogin } from '../models/RespuestaLogin';
import { RespuestaUsuarioJwt } from '../models/RespuestaUsuarioJwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage {
  nombreUsuario: string = '';
  contrasena: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router ) { }

  login() {
    const loginData: RespuestaLogin = {
      nombreUsuario: this.nombreUsuario,
      contrasena: this.contrasena
    };
    this.usuarioService.login(this.nombreUsuario, this.contrasena).subscribe({
      next: (res: RespuestaUsuarioJwt) => {
        console.log('Login exitoso', res);
        localStorage.setItem('token', res.token);
        localStorage.setItem('userId', res.id); 
        this.router.navigate(['/main/tareas']);
      },
      error: (e: any) => {
        console.error('Error en el login', e);
      }
    });
    
  }

}
