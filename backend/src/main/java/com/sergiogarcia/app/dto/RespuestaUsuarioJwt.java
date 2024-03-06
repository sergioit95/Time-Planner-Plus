package com.sergiogarcia.app.dto;

import com.sergiogarcia.app.modelos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RespuestaUsuarioJwt extends RespuestaUsuario{

	private String token;
	
	public RespuestaUsuarioJwt(RespuestaUsuario respuestaUsuario) {
		id = respuestaUsuario.getId();
		nombreUsuario = respuestaUsuario.getNombreUsuario();
		nombre = respuestaUsuario.getNombre();
		apellidos = respuestaUsuario.getApellidos();
	}
	
	  public static RespuestaUsuarioJwt of(Usuario usuario, String token) {
	        RespuestaUsuario respuestaUsuario = RespuestaUsuario.deUsuario(usuario);
	        RespuestaUsuarioJwt resultado = new RespuestaUsuarioJwt(respuestaUsuario);
	        resultado.setToken(token);
	        resultado.setId(usuario.getId().toString()); 
	        return resultado;
	    }
}
