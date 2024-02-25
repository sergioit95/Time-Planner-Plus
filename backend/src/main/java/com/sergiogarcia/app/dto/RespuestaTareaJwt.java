package com.sergiogarcia.app.dto;

import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RespuestaTareaJwt extends RespuestaTarea {

	private String token;
	
	public RespuestaTareaJwt(RespuestaTarea respuestaTarea) {
		id = respuestaTarea.getId();
		titulo = respuestaTarea.getTitulo();
		descripcion = respuestaTarea.getDescripcion();
		fechaCreacion = respuestaTarea.getFechaCreacion();
	}
	
	public static RespuestaTareaJwt of (Tarea tarea, String token) {
		RespuestaTareaJwt resultado = new RespuestaTareaJwt(RespuestaTarea.deTarea(tarea));
		resultado.setToken(token);;
		return resultado;
	}
}
