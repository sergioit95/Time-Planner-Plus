package com.sergiogarcia.app.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

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
	
	

}
