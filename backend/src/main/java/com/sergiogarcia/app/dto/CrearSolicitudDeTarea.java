package com.sergiogarcia.app.dto;

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
	

}
