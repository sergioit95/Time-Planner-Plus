package com.sergiogarcia.app.modelos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails{
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator",
			parameters = {
					@Parameter(
							name = "uuid_gen_strategy_class",
							value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
			})
	
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@NaturalId
	@Column(name ="nombre_usuario", unique = true, updatable = false)
	private String nombreUsuario;
	
	@Column(name = "contrasena", nullable = false)
	private String contrasena;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellidos", nullable = false)
	private String apellidos;

	
	@Builder.Default
	private boolean cuentaNoExpirada = true;
	
	@Builder.Default
	private boolean cuentaNoBloqueada = true;
	
	@Builder.Default
	private boolean credencialesNoExpiradas = true;
	
	@Builder.Default
	private boolean cuentaHabilitada = true;
	

	
	@OneToMany(mappedBy = "usuario")
    private Set<Tarea> tareas;
	
	@CreatedDate
	private LocalDateTime creadoEn;
	
	@Builder.Default
	private LocalDateTime ultimoCambioContrasena = LocalDateTime.now(); 
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasena;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return cuentaNoExpirada;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return cuentaNoBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credencialesNoExpiradas;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return cuentaHabilitada;
	}
	

}
