package com.sergiogarcia.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.models.Usuario;
import com.sergiogarcia.app.repositories.AdministradorRepository;
import com.sergiogarcia.app.repositories.UsuarioRepository;

@Service
public class AdministradorService {
	private final UsuarioRepository usuarioRepository;

	@Autowired
	public AdministradorService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	

}
