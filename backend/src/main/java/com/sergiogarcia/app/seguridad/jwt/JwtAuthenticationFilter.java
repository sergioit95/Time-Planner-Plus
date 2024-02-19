package com.sergiogarcia.app.seguridad.jwt;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.ServicioUsuario;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final ServicioUsuario servicioUsuario;
	private final JwtProvider jwtProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = obtenerTokenJwtDeSolicitud(request);
		
		try {
			if (StringUtils.hasText(token) && jwtProvider.validarToken(token)) {
				UUID idUsuario = jwtProvider.obtenerIdUsuarioDeTokenJwt(token);
				Optional<Usuario> resultado = servicioUsuario.buscarPorIdUsuario(idUsuario);
				
				if (resultado.isPresent())  {
					Usuario usuario = resultado.get();
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(
									usuario,
									null,
									usuario.getAuthorities()
									);
					
					authenticationToken.setDetails(new WebAuthenticationDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}catch(Exception ex) {
			log.info("Error de autenticación usando token JWT: "+ex.getMessage());
		}
	filterChain.doFilter(request, response);
		
	}
	
	
	
	private String obtenerTokenJwtDeSolicitud(HttpServletRequest request) {
		String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)) {
			return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
		}
		return null;
	}

}
