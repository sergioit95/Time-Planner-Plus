import { Component } from '@angular/core';
import { UsuarioService } from '../services/usuario/usuario.service';
import { CrearSolicitudDeUsuario } from '../models/CrearSolicitudDeUsuario';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.page.html',
  styleUrls: ['./registro.page.scss'],
})
export class RegistroPage {

  nombreUsuario!: string;
  contrasena!: string;
  nombre!: string;
  apellidos!: string;

  constructor(private usuarioService: UsuarioService) {}

  registrar() {
    const crearSolicitudDeUsuario: CrearSolicitudDeUsuario = {
      nombreUsuario: this.nombreUsuario,
      contrasena: this.contrasena,
      nombre: this.nombre,
      apellidos: this.apellidos,
    };

    this.usuarioService.crearUsuario(crearSolicitudDeUsuario).subscribe({
      next: (usuario: any) => {
        console.log('Usuario registrado con éxito', usuario);
        // Redirigir al usuario o mostrar mensaje de éxito
      },
      error: (error: any) => {
        console.error('Error al registrar el usuario', error);
        // Mostrar mensaje de error
      },
    });
  }
}




