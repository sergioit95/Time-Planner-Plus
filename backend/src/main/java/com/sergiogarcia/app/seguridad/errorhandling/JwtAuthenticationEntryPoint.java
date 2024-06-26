package com.sergiogarcia.app.seguridad.errorhandling;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setHeader("WWW-Authenticate", "Bearer");
		response.setContentType("application/json");
		
		response.getWriter()
			.println(objectMapper.writeValueAsString(
						Map.of("error",authException.getMessage())
					));
		
	}
	
	
}
