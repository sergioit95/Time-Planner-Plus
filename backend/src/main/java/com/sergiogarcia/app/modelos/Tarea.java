package com.sergiogarcia.app.modelos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Tarea")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Set<EstadoTarea> estados;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	
	
	
}
