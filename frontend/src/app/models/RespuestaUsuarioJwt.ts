import { Usuario } from "./Usuario";

export interface RespuestaUsuarioJwt {
    usuario: Usuario;
    token: string;
  }