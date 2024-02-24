package com.sergiogarcia.app.controladores;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.sergiogarcia.app.dto.CrearSolicitudDeTarea;
import com.sergiogarcia.app.dto.RespuestaTarea;
import com.sergiogarcia.app.modelos.EstadoTarea;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.ServicioTarea;
import com.sergiogarcia.app.servicios.ServicioUsuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor

public class ControladorTarea {
	
	@Autowired
	private final ServicioTarea servicioTarea;
	
	@GetMapping
	public ResponseEntity<List<Tarea>> listarTareas(@AuthenticationPrincipal Usuario usuario){
		List<Tarea> tareas = servicioTarea.listarTareas();
		return ResponseEntity.ok(tareas);
		
	}
    
	@GetMapping("/{id}")
	public ResponseEntity<Tarea> buscarTareaPorId(@PathVariable UUID id){
		Optional<Tarea> tarea = servicioTarea.buscarPorIdTarea(id);
		if (tarea.isPresent()) {
			return ResponseEntity.ok(tarea.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Tarea> crearTarea(@RequestBody CrearSolicitudDeTarea crearSolicitudDeTarea, @RequestParam Set<EstadoTarea> estado) throws Exception {

		try {
			Tarea tarea = servicioTarea.crearTarea(crearSolicitudDeTarea, estado);
			return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Tarea> editarTarea(@PathVariable UUID id, @RequestBody Tarea tarea){
		tarea.setId(id);
		Optional<Tarea> tareaActualizada = servicioTarea.editarTarea(tarea);
		if (tareaActualizada.isPresent()) {
			return ResponseEntity.ok(tareaActualizada.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarTareaPorId(@PathVariable UUID id){
		servicioTarea.eliminarTareaPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
