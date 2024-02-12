package com.sergiogarcia.app.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sergiogarcia.app.dto.CrearSolicitudDeTarea;
import com.sergiogarcia.app.modelos.EstadoTarea;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.repositorios.RepositorioTarea;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioTarea {

	private final RepositorioTarea repositorioTarea;
	
	public Tarea crearTarea(CrearSolicitudDeTarea crearSolicitudDeTarea, Set<EstadoTarea> estados) {
		Tarea tarea = Tarea.builder()
					.titulo(crearSolicitudDeTarea.getTitulo())
					.descripcion(crearSolicitudDeTarea.getDescripcion())
					.estados(estados)
					.build();
		return repositorioTarea.save(tarea);
	}
	
	public Tarea crearTareaConEstadoPendiente(CrearSolicitudDeTarea crearSolicitudDeTarea) {
		return crearTarea(crearSolicitudDeTarea, Set.of(EstadoTarea.PENDIENTE));
	}
	
	public Tarea crearTareaConEstadoEnProgreso(CrearSolicitudDeTarea crearSolicitudDeTarea) {
		return crearTarea(crearSolicitudDeTarea, Set.of(EstadoTarea.EN_PROGRESO));
	}
	
	public Tarea crearTareaConEstadoTerminada(CrearSolicitudDeTarea crearSolicitudDeTarea) {
		return crearTarea(crearSolicitudDeTarea, Set.of(EstadoTarea.TERMINADA));
	}
	
	public List<Tarea> listarTareas() {
		return repositorioTarea.findAll();
	}
	
	public Optional<Tarea> buscarPorIdTarea(UUID id) {
		return repositorioTarea.findById(id);
	}
	
	public Optional<Tarea> buscarPorTituloTarea(String titulo) {
		return repositorioTarea.findFirstByTituloTarea(titulo);
	}
	
	public Optional<Tarea> editarTarea(Tarea tarea) {
		return repositorioTarea.findById(tarea.getId())
				.map(t -> {
					t.setTitulo(tarea.getTitulo());
					t.setDescripcion(tarea.getDescripcion());
					
					return repositorioTarea.save(tarea);
				});
	}
	
	
	public void eliminarTareaPorId(UUID id) {
		if(repositorioTarea.existsById(id)) {			
		}else {
			repositorioTarea.deleteById(id);	
		}
	}
	
	public void eliminarTarea(Tarea tarea) {
		eliminarTareaPorId(tarea.getId());
	}
}
