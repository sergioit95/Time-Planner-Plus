package com.sergiogarcia.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sergiogarcia.app.modelos.Tarea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RespuestaTarea {
	
	protected String id;
	protected String titulo, descripcion;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	protected LocalDateTime fechaCreacion;
	
	//Método que devuelve la respuesta con los campos que he querido que se muesten al cliente, una vez que se ha registrado una tarea
	public static RespuestaTarea deTarea(Tarea tarea) {
		
		return RespuestaTarea.builder()
				.id(tarea.getId().toString())
				.titulo(tarea.getTitulo())
				.descripcion(tarea.getDescripcion())
				.fechaCreacion(tarea.getCreadoEn())
				.build();
	}
	
}
