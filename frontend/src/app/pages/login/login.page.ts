import { Component } from '@angular/core';
import { UsuarioService } from '../../services/usuario/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage {
  nombreUsuario: string = '';
  contrasena: string = '';
  loginError: boolean = false; // Indica si hay un error de login

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  login() {
    this.loginError = false;
    if (this.nombreUsuario && this.contrasena) {
      // Realiza la llamada al servicio de login
      this.usuarioService.login(this.nombreUsuario, this.contrasena).subscribe({
        next: (res) => {
          console.log('Login exitoso', res);
          localStorage.setItem('token', res.token);
          // Redirige al usuario a la página principal o donde corresponda
          this.router.navigate(['/main/tareas']);
        },
        error: (error) => {
          console.error('Error en el login', error);
          this.loginError = true; // Muestra mensaje de error en el template
        }
      });
    }
  }
}
