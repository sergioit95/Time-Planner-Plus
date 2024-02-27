import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../services/usuario/usuario.service';
import { Router } from '@angular/router';
import { RespuestaLogin } from '../models/RespuestaLogin';

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
    }
    this.usuarioService.login(loginData.nombreUsuario, loginData.contrasena).subscribe({
      next: (res) => {
        console.log('Login exitoso', res);
        // Guarda el token en el localStorage
        localStorage.setItem('token', res.token);
        // Redirige al usuario a otra página después del inicio de sesión
        this.router.navigate(['/http://localhost:8081/tareas']);
      },
      error: (e) => {
        console.error('Error en el login', e);
      }
    });
  }

}
