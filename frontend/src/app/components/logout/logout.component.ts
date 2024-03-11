import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario/usuario.service';
@Component({
  selector: 'app-logout',
  template:  '',
})
export class LogoutComponent implements OnInit {

  constructor(private usuarioService: UsuarioService) { }
  ngOnInit(): void {
    this.logout();
  }


  logout(): void {
    this.usuarioService.logout();
  }

}
