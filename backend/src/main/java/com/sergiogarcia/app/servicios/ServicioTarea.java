package com.sergiogarcia.app.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sergiogarcia.app.dto.CrearSolicitudDeTarea;
import com.sergiogarcia.app.modelos.EstadoTarea;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.repositorios.RepositorioTarea;
import com.sergiogarcia.app.repositorios.RepositorioUsuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioTarea {

	private final RepositorioTarea repositorioTarea;
	private final RepositorioUsuario repositorioUsuario;
	
	public Tarea crearTarea(CrearSolicitudDeTarea crearSolicitudDeTarea, Set<EstadoTarea> estado) throws Exception {
		Usuario usuario = repositorioUsuario.findById(crearSolicitudDeTarea.getUsuario().getId()).orElseThrow(() -> new 
				Exception("No se encontró el usuario con el id "+crearSolicitudDeTarea.getUsuario().getId()));
		Tarea tarea = Tarea.builder()
					.titulo(crearSolicitudDeTarea.getTitulo())
					.descripcion(crearSolicitudDeTarea.getDescripcion())
					.estados(estado)
					.usuario(usuario)
					.build();
		return repositorioTarea.save(tarea);
	}
	
	
	
	public List<Tarea> listarTareas() {
		return repositorioTarea.findAll();
	}
	
	public Optional<Tarea> buscarPorIdTarea(UUID id) {
		return repositorioTarea.findById(id);
	}
	
	public Optional<Tarea> buscarPorTituloTarea(String titulo){
		return repositorioTarea.findFirstByTitulo(titulo);
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
