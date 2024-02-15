package com.sergiogarcia.app.controladores;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.ServicioTarea;
import com.sergiogarcia.app.servicios.ServicioUsuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ControladorTarea {
	
	
	private final ServicioTarea servicioTarea;
	
	@GetMapping("/tareas")
	public ResponseEntity<List<Tarea>> listarTareas(){
		List<Tarea> tareas = servicioTarea.listarTareas();
		return ResponseEntity.ok(tareas);
		
	}
    
	@GetMapping("/tareas/{id}")
	public ResponseEntity<Tarea> buscarTareaPorId(@PathVariable UUID id){
		Optional<Tarea> tarea = servicioTarea.buscarPorIdTarea(id);
		if (tarea.isPresent()) {
			return ResponseEntity.ok(tarea.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/tareas/pendientes")
	public ResponseEntity<Tarea> crearTareaPendiente(@RequestBody CrearSolicitudDeTarea crearSolicitudDeTarea) throws Exception {
	    Tarea tareaCreada = servicioTarea.crearTareaConEstadoPendiente(crearSolicitudDeTarea);
	    return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
	}
	
	@PostMapping("/tareas/en-progreso")
	public ResponseEntity<Tarea> crearTareaEnProgreso(@RequestBody CrearSolicitudDeTarea crearSolicitudDeTarea) throws Exception{
		Tarea tarea = servicioTarea.crearTareaConEstadoEnProgreso(crearSolicitudDeTarea);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
	}
	
	@PostMapping("/tareas/terminadas")
	public ResponseEntity<Tarea> crearTareaCompletada(@RequestBody CrearSolicitudDeTarea crearSolicitudDeTarea) throws Exception{
		Tarea tarea = servicioTarea.crearTareaConEstadoTerminada(crearSolicitudDeTarea);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarea);
	}
	
	@PutMapping("/tareas/{id}")
	public ResponseEntity<Tarea> editarTarea(@PathVariable UUID id, @RequestBody Tarea tarea){
		tarea.setId(id);
		Optional<Tarea> tareaActualizada = servicioTarea.editarTarea(tarea);
		if (tareaActualizada.isPresent()) {
			return ResponseEntity.ok(tareaActualizada.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<Void> eliminarTareaPorId(@PathVariable UUID id){
		servicioTarea.eliminarTareaPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/tareas")
	public ResponseEntity<Void> eliminarTarea(@PathVariable Tarea tarea){
		servicioTarea.eliminarTarea(tarea);
		return ResponseEntity.noContent().build();
	}
}
