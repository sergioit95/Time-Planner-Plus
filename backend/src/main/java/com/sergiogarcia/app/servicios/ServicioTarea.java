package com.sergiogarcia.app.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.dto.CrearSolicitudDeTarea;
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
	
	public Tarea crearTarea(CrearSolicitudDeTarea crearSolicitudDeTarea) throws Exception {
		String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = repositorioUsuario.findFirstByNombreUsuario(nombreUsuario).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: "+nombreUsuario));
		Tarea tarea = Tarea.builder()
					.titulo(crearSolicitudDeTarea.getTitulo())
					.descripcion(crearSolicitudDeTarea.getDescripcion())
					.estaCompletada(false)
					.usuario(usuario)
					.build();
		return repositorioTarea.save(tarea);
	}
	
	public Tarea completarTarea(UUID id) throws Exception {
		Tarea tarea = repositorioTarea.findById(id).orElseThrow(() -> new Exception("Tarea no encontrada con el ID: " + id));
		tarea.setEstaCompletada(true);
		return repositorioTarea.save(tarea);
	}
	
	public Tarea desmarcarTarea(UUID id) throws Exception {
        Tarea tarea = repositorioTarea.findById(id).orElseThrow(() -> new Exception("Tarea no encontrada con el ID: " + id));
        tarea.setEstaCompletada(false);
        return repositorioTarea.save(tarea);
    }
	
	public List<Tarea> obtenerTareasCompletadas() {
        return repositorioTarea.findAll().stream().filter(tarea -> tarea.getEstaCompletada()).collect(Collectors.toList());
    }
	
	public List<Tarea> obtenerTareasNoCompletadas() {
        return repositorioTarea.findAll().stream().filter(tarea -> !tarea.getEstaCompletada()).collect(Collectors.toList());
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
	

	
	public Optional<Tarea> editarTarea(UUID id, CrearSolicitudDeTarea tarea) {
		return repositorioTarea.findById(id)
				.map(t -> {
					t.setTitulo(tarea.getTitulo());
					t.setDescripcion(tarea.getDescripcion());
					t.setEstaCompletada(tarea.getEstaCompletada());
					
					return repositorioTarea.save(t);
				});
	}
	
	
	public void eliminarTareaPorId(UUID id) throws Exception {
		if(repositorioTarea.existsById(id)) {
			repositorioTarea.deleteById(id);
		}else {
			throw new Exception("Tarea no encontrada con el ID: "+id);
		}
	}
	
	
}
