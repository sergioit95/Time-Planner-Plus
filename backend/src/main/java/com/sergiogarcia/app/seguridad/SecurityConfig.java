package com.sergiogarcia.app.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
/*
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	public AuthenticationManager authenticationManager(HttpSecurity http) 
		throws Exception {
			AuthenticationManagerBuilder authenticationManagerBuilder = 
					http.getSharedObject(AuthenticationManagerBuilder.class);
			AuthenticationManager authenticationManager = 
					authenticationManagerBuilder
					.userDetailsService(userDetailsService)
					.passwordEncoder(passwordEncoder)
					.and().build();
			
			return authenticationManager;
		}
	

	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**", "/auth/register");
    }
}*/

