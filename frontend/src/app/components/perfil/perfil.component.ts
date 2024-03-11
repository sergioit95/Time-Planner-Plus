import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss'],
})
export class PerfilComponent implements OnInit {
  usuario: any = {};
  nuevaContrasena: string = '';
  mostrarFormularioEdicion: boolean = false;

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit(): void {
    this.cargarUsuario();
  }

  cargarUsuario(): void {
    const usuarioId = localStorage.getItem('userId');
    if (usuarioId) {
      this.usuarioService.obtenerUsuarioPorId(usuarioId).subscribe({
        next: (datosUsuario: any) => {
          this.usuario = datosUsuario;
        },
        error: (error: any) => console.error('Error al obtener datos del usuario', error),
      });
    } else {
      console.error('ID de usuario no encontrado');
      this.router.navigate(['/auth/login']);
    }
  }

  guardarCambios(): void {
    const usuarioId = localStorage.getItem('userId');
    if (this.usuario && usuarioId) {
      this.usuarioService.editarUsuario(this.usuario.id, {
        nombre: this.usuario.nombre,
        apellidos: this.usuario.apellidos,
      }).subscribe({
        next: (respuesta) => {
          console.log('Perfil actualizado', respuesta);
          this.mostrarFormularioEdicion = false;
          this.cargarUsuario();
        },
        error: (error) => console.error('Error al actualizar perfil', error),
      });
    }

    if (this.nuevaContrasena) {
      // Actualización de la contraseña
      this.usuarioService.editarContrasena(this.usuario.id, this.nuevaContrasena).subscribe({
        next: () => {
          console.log('Contraseña actualizada');
          this.nuevaContrasena = ''; // Limpiar el campo de nueva contraseña después de la actualización exitosa
        },
        error: (error) => console.error('Error al actualizar contraseña', error),
      });
    }
  }
}
