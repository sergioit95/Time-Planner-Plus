package com.sergiogarcia.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administrador")
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_administrador;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String contrasena;
	

	
}
