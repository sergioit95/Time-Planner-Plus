package com.sergiogarcia.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiogarcia.app.models.Usuario;
import com.sergiogarcia.app.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {	
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario, @AuthenticationPrincipal Usuario currentUser) throws Exception {
        return usuarioService.updateUsuario(usuario, currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id, @AuthenticationPrincipal Usuario currentUser) throws Exception {
        usuarioService.deleteUsuario(id, currentUser);
    }
}