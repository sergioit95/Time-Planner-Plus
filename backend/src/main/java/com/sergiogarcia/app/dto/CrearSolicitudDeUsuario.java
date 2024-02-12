package com.sergiogarcia.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearSolicitudDeUsuario {

	private String nombreUsuario, contrasena, contrasenaVerificada, nombre, apellidos;
}
