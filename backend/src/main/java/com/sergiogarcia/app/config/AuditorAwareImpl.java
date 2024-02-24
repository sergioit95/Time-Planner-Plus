	package com.sergiogarcia.app.config;
	
	import java.util.Optional;
	import java.util.UUID;
	
	import org.springframework.data.domain.AuditorAware;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.context.SecurityContextHolder;
	
	import com.sergiogarcia.app.modelos.Usuario;
	
	public class AuditorAwareImpl implements AuditorAware<String> {
	
		@Override
		public Optional<String> getCurrentAuditor() {
			Authentication authentication =
					SecurityContextHolder.getContext().getAuthentication();
			return Optional.ofNullable(authentication)
					.map(auth -> (Usuario) auth.getPrincipal())
					.map(Usuario::getId)
					.map(UUID::toString);
					
		}
		
	}
