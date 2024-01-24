package com.sergiogarcia.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tarea")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tarea;
	
	private String titulo;
	private String descripcion;
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Long getId_tarea() {
		return id_tarea;
	}

	public void setId_tarea(Long id_tarea) {
		this.id_tarea = id_tarea;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
