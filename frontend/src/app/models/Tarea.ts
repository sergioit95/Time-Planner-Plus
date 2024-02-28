import { Usuario } from "./Usuario";

export interface Tarea {
    id: string; 
    titulo: string;
    descripcion: string;
    estaCompletada: boolean;
    usuario?: Usuario; 
    creadoEn?: Date;
  }
  

  