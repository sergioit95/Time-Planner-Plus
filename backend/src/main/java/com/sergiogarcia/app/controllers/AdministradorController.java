package com.sergiogarcia.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiogarcia.app.models.Usuario;
import com.sergiogarcia.app.services.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

	private final AdministradorService administradorService;
	
	@Autowired
	public AdministradorController(AdministradorService administradorService) {
		this.administradorService = administradorService;
	}
	
	@PostMapping("/usuarios")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return administradorService.crearUsuario(usuario);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public void eliminarUsuario(@PathVariable Long id) {
		administradorService.eliminarUsuario(id);
	}
}
