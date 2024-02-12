package com.sergiogarcia.app.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ServicioUsuario servicioUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return servicioUsuario.buscarPorNombreUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario con nombre de usuario no encontrado " + username));
	}

}
