package com.sergiogarcia.app.seguridad.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.modelos.Usuario;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;

@Log
@Service
public class JwtProvider {
	
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.duration}")
	private int jwtLifeInDays;
	
	private JwtParser jwtParser;
	
	private SecretKey secretKey;
	
	
	@PostConstruct
	public void init() {
		secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		
		jwtParser = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build();
	}
	
	public String generarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date expiracionTokenDateTime = 
				Date.from(
						LocalDateTime
							.now()
							.plusDays(jwtLifeInDays)
							.atZone(ZoneId.systemDefault())
							
						);
		
		return Jwts.builder()
				.setHeaderParam("typ", TOKEN_TYPE)
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(expiracionTokenDateTime)
				.signWith(secretKey)
				.compact();
	}
	
	public boolean validarToken(String token) {
		try {
			jwtParser.parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException ex) {
			log.info("Error con el token "+ex.getMessage());
		}
		return false;
	}
}
