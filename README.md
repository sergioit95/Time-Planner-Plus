# Aplicación para gestionar la productividad del día a día 
Proyecto Fin de Ciclo Formativo de Grado Superior en Desarrollo de Aplicaciones Multiplataforma.
Realizado por Sergio García Herrera en Marzo de 2024.

# Índice

1. Descripción de mi proyecto 
2. Funcionalidad
3. Despliegue
4. Posibles Mejoras de la aplicación
5. Problemas encontrados
6. Conclusión

# 1. Descripción de mi proyecto   
Es una aplicación multiplataforma e híbrida desarrollada con Ionic y Angular en la parte de la interacción de usuario (front-end),  y con SpringBoot, JWT y MySQL para la parte que hace gestión del servidor y la base de datos.
El principal objetivo de esta aplicación es que aquellas personas con una falta de organizacion, sean capaces de mejorar su productividad diaria anotando las tareas que pretenden hacer, ahorrandose asi un tiempo valioso para disfrutar de otras cosas. Por ejemplo permitiendo la creación, modificación y eliminación de tareas. Teniendo un estado al inicializar la tarea de no completada y una vez que se complete tener la opcion de pasar a la lista de tareas completadas. 


# 2. Funcionalidad
La funcionalidad de mi proyecto se centra en lo siguiente:

- **Panel de autenticación**: La persona una vez que abra la aplicación se le abrirá este panel, donde habrá dos "Tabs", uno para loguearse y otro para registrarse. 

- **Panel de tareas**: El usuario que este registrado y logueado con éxito podra creer, modificar, eliminiar, completar y desmarcar tareas.

- **Panel de edición de perfil**: El usuario puede editar su perfil como nombre y apellidos, y cambiar la contraseña en esta sección.

- **Implementación técnica**: La aplicacion es implementada con Spring Boot, Spring Security y JWT para el procesamiento de autenticación. Para la base de datos he usado MySQL. Y para la parte del front-end, he usado Ionic y Angular.

- **Aumento de la productividad**: Este MVP de aplicación, creo que puede ser beneficiosa para mucha gente que como yo, es un caos organizando las tareas de su dia a dia.

# 3. Despliegue
## MySQL en Local y Tomcat
Para manejar los datos en el frontend de la API Rest de este proyecto, hay que hacer uso de una base de datos y un framework que se pueda comunicar con ella (yo he utilizado Spring Boot y MySQL).

**BackEnd**
Yo he creado este script en MySQL que crea la base de datos, la borra si ya existe en el gestor de base de datos, crea las tablas Usuario y Tarea, con una relación de uno a muchos. 
- Script:
DROP DATABASE IF EXISTS time_planner_plus;

CREATE DATABASE time_planner_plus;

USE time_planner_plus;

CREATE TABLE Usuario (
  id BINARY(16) PRIMARY KEY,
  nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
  contrasena VARCHAR(200) NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(50) NOT NULL
);

CREATE TABLE Tarea (
  id BINARY(16) PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  descripcion TEXT,
  esta_completada BOOLEAN NOT NULL DEFAULT FALSE,  
  usuario_id BINARY(16) NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

## FrontEnd
Hay que tener instalado Node.js para hacer uso de Angular. Una vez instalado se podra crear el proyecto con ng start (nombre_del_proyecto). Una vez creado podremos crear componentes con ng g c nombre_del_componente y para crear los servicios ng g s nombre_del_servicio. Una vez tenemos los componentes, los servicios que se comunican con la API que tenemos en SpringBoot podremos iniciar el proyecto con ng s -o.

## 4. Posibles Mejoras de la aplicación
Estas son las mejoras que me gustaría hacer en un futuro: 
- Arreglar el problema que hay cuando se quiere editar la información del usuario.
- Añadir una funcionalidad para poner alarmas a la hora que se desea hacer la tarea.
- Añadir login con Google y Facebook.

## 5. Problemas encontrados
Me he encontrado con los siguientes problemas:
- Intente hacer la implementación con dos roles de usuario, uno de Admin y otro de User, pero al final no supe implementarlo.
- Problemas con las rutas y los CORS, para comunicarse Angular con Springboot correctamente.
- Problemas no solucionados aún con la edición del perfil de usuario.

## 6. Conclusión
En conclusión, me llevo una gran experiencia realizando este proyecto, porque creía que no podría ser capaz de hacer funcionar una aplicación full stack. Me ha servido mucho este proyecto para ver que soy capaz de investigar por mi cuenta y filtrar bien la información que hay en internet para luego implementarla en mis aplicaciones, y seguir en continuo aprendizaje.
