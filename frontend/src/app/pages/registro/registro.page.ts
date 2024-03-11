import { Component } from '@angular/core';
import { UsuarioService } from '../../services/usuario/usuario.service';
import { CrearSolicitudDeUsuario } from '../../models/CrearSolicitudDeUsuario';
import { Router } from '@angular/router';

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

  constructor(private router: Router, private usuarioService: UsuarioService) {}

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
        this.router.navigate(['auth/login']);
      },
      error: (error: any) => {
        console.error('Error al registrar el usuario', error);
      },
    });
  }
}




