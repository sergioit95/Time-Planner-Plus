package com.sergiogarcia.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.models.Usuario;
import com.sergiogarcia.app.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario updateUsuario(Usuario usuario, Usuario currentUser) throws Exception {
        if (!usuario.equals(currentUser)) {
            throw new Exception("No tienes permiso para editar este usuario");
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id, Usuario currentUser) throws Exception {
        if (!id.equals(currentUser.getId_usuario())) {
            throw new Exception("No tienes permiso para eliminar este usuario");
        }
        usuarioRepository.deleteById(id);
    }
    
}