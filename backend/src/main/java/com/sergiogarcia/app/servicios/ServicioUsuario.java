package com.sergiogarcia.app.servicios;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.dto.CrearSolicitudDeUsuario;
import com.sergiogarcia.app.modelos.RolUsuario;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.repositorios.RepositorioUsuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioUsuario {
	private final PasswordEncoder passwordEncoder;
	private final RepositorioUsuario repositorioUsuario;
	
	public Usuario crearUsuario(CrearSolicitudDeUsuario crearSolicitudDeUsuario, Set<RolUsuario> roles) {
		Usuario usuario = Usuario.builder()
					.nombreUsuario(crearSolicitudDeUsuario.getNombreUsuario())
					.contrasena(passwordEncoder.encode(crearSolicitudDeUsuario.getContrasena()))
					.nombre(crearSolicitudDeUsuario.getNombre())
					.apellidos(crearSolicitudDeUsuario.getApellidos())
					.roles(roles)
					.build();
		return repositorioUsuario.save(usuario);
	}
	
	public Usuario crearUsuarioConRolUsuario(CrearSolicitudDeUsuario crearSolicitudDeUsuario) {
		return crearUsuario(crearSolicitudDeUsuario, Set.of(RolUsuario.USER));
	}
	
	public Usuario crearUsuarioConRolAdmin(CrearSolicitudDeUsuario crearSolicitudDeUsuario) {
		return crearUsuario(crearSolicitudDeUsuario, Set.of(RolUsuario.ADMIN));
	}
	
	public List<Usuario> listarUsuarios() {
		return repositorioUsuario.findAll();
	}
	
	public Optional<Usuario> buscarPorIdUsuario(UUID id) {
		return repositorioUsuario.findById(id);
	}
	
	public Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario) {
		return repositorioUsuario.findFirstByNombreUsuario(nombreUsuario);
	}
	
	public Optional<Usuario> editarUsuario(Usuario usuario) {
		return repositorioUsuario.findById(usuario.getId())
				.map(u -> {
					u.setNombre(usuario.getNombre());
					u.setApellidos(usuario.getApellidos());
					return repositorioUsuario.save(usuario);
				});
	}
	
	public Optional<Usuario> editarContrasena(UUID idUsuario, String nuevaContrasena) {
		return repositorioUsuario.findById(idUsuario)
				.map(u -> {
					u.setContrasena(passwordEncoder.encode(nuevaContrasena));
					return repositorioUsuario.save(u);
				});
	}
	
	public void eliminarUsuarioPorId(UUID id) {
		if(repositorioUsuario.existsById(id)) {			
		}else {
			repositorioUsuario.deleteById(id);	
		}
	}
	
	public void eliminarUsuario(Usuario usuario) {
		eliminarUsuarioPorId(usuario.getId());
	}
}