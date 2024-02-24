package com.sergiogarcia.app.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import com.sergiogarcia.app.modelos.EstadoTarea;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearSolicitudDeTarea {

	private String titulo, descripcion;
	private Usuario usuario;
	
	public static EstadoTarea calcularEstado(Tarea tarea) {
		  // Obtener los estados de la tarea
		  Set<EstadoTarea> estados = tarea.getEstados();

		  // Si la tarea está completada, se devuelve ese estado
		  if (estados.contains(EstadoTarea.TERMINADA)) {
		    return EstadoTarea.TERMINADA;
		  }

		  // Si la tarea está en progreso, se devuelve ese estado
		  if (estados.contains(EstadoTarea.EN_PROGRESO)) {
		    return EstadoTarea.EN_PROGRESO;
		  }

		  // Si la tarea está pendiente, se devuelve ese estado
		  if (estados.contains(EstadoTarea.PENDIENTE)) {
		    return EstadoTarea.PENDIENTE;
		  }

		  // Si la tarea tiene otro estado, se devuelve ese estado
		  return estados.iterator().next();
		}


}
