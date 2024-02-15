package com.sergiogarcia.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sergiogarcia.app.modelos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespuestaUsuario {

	private String id;
	private String nombreUsuario, nombre, apellidos;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaCreacion;
	
	//Método que devuelve la respuesta con los campos que he querido que se muesten al cliente, una vez que se ha registrado un usuario
	public static RespuestaUsuario deUsuario(Usuario usuario) {
		
		return RespuestaUsuario.builder()
				.id(usuario.getId().toString())
				.nombreUsuario(usuario.getNombreUsuario())
				.nombre(usuario.getNombre())
				.apellidos(usuario.getApellidos())
				.fechaCreacion(usuario.getCreadoEn())
				.build();
	}
}
