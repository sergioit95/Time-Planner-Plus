import { Usuario } from "./Usuario";

export class Tarea {
    id!: string; 
    titulo!: string;
    descripcion!: string;
    estaCompletada!: boolean;
    usuario?: Usuario; 
    creadoEn?: Date;

    constructor(init?: Partial<Tarea>) {
      Object.assign(this, init);
    }
  }
  

  