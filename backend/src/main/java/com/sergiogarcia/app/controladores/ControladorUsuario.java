package com.sergiogarcia.app.controladores;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sergiogarcia.app.dto.CrearSolicitudDeUsuario;
import com.sergiogarcia.app.dto.RespuestaUsuario;
import com.sergiogarcia.app.modelos.RolUsuario;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.ServicioUsuario;

import aj.org.objectweb.asm.Type;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ControladorUsuario {	
   
    private final ServicioUsuario servicioUsuario;
    
    //Método que crea el endpoint y la solicitud para registrar usuarios con rol de usuario
    @PostMapping("/autenticacion/registro")
    public ResponseEntity<RespuestaUsuario> crearUsuarioConRolUsuario(
    		@RequestBody CrearSolicitudDeUsuario crearSolicitudDeUsuario
    		){
    	Usuario usuario = servicioUsuario.crearUsuarioConRolUsuario(crearSolicitudDeUsuario);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(RespuestaUsuario.deUsuario(usuario));
    }
    
    //Método que crea el endpoint y la solicitud para registrar usuarios con rol de administrador
    @PostMapping("/autenticacion/registro/admin")
    public ResponseEntity<RespuestaUsuario> crearUsuarioConRolAdmin(
    		@RequestBody CrearSolicitudDeUsuario crearSolicitudDeUsuario
    		){
    	Usuario usuario = servicioUsuario.crearUsuarioConRolAdmin(crearSolicitudDeUsuario);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(RespuestaUsuario.deUsuario(usuario));
    }
    
    //Método que crea el endpoint y la solicitud para listar todos los usuarios. Donde solo los administradores tienen acceso
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("autenticacion/panel-administracion")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
    	List<Usuario> usuarios = servicioUsuario.listarUsuarios();
    	return ResponseEntity.ok(usuarios);
    	
    }
    
    //Método que crea el endpoint y la solicitud para listar todos los usuarios. Donde solo los administradores tienen acceso
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("autenticacion/panel-administracion/{nombreUsuario}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nombreUsuario){
    	Optional<Usuario> usuario = servicioUsuario.buscarPorNombreUsuario(nombreUsuario);
    	return ResponseEntity.ok(usuario.get());
    	
    }
    
    //Método que crea el endpoint y la solicitud para editar un usuario por su id. Donde solo los administradores tienen acceso
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/autenticacion/perfil-admin/{id}")
	public ResponseEntity<Usuario> editarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario){
		Optional<Usuario> usuarioActualizado = servicioUsuario.editarUsuario(usuario);
		if (usuarioActualizado.isPresent()) {
			return ResponseEntity.ok(usuarioActualizado.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

    //Método que crea el endpoint y la solicitud para editar la contraseña de un usuario. Donde solo los administradores tienen acceso
    @PreAuthorize("hasRole('ADMIN')")
   	@PutMapping("/autenticacion/perfil-admin/{id}/contrasena")
   	public ResponseEntity<Usuario> editarContrasena(@PathVariable UUID id, @RequestBody String nuevaContrasena){
   		Optional<Usuario> usuario = servicioUsuario.editarContrasena(id, nuevaContrasena);
   		if (usuario.isPresent()) {
   			return ResponseEntity.ok(usuario.get());
   		}else {
   			return ResponseEntity.notFound().build();
   		}
   	}
    
    //Método que crea el endpoint y la solicitud para eliminar un usuario pasandole su id como parámetro. Donde solo los administradores tienen acceso
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/autenticacion/panel-administracion/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable UUID id){
    	servicioUsuario.eliminarUsuarioPorId(id);
		return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}