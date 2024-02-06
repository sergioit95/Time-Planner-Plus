export class Usuario{
    id: number;
    nombre: string;
    apellidos: string;
    email: string;
    contrasena: string;
  
    constructor(id: number, nombre: string, apellidos: string, email: string, contrasena: string) {
      this.id = id;
      this.nombre = nombre;
      this.apellidos = apellidos;
      this.email = email;
      this.contrasena = contrasena;
    }

}