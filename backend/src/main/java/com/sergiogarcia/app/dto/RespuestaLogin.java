package com.sergiogarcia.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaLogin {
	private String nombreUsuario;
	private String contrasena;

}
