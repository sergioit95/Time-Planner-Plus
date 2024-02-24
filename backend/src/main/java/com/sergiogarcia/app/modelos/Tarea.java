package com.sergiogarcia.app.modelos;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@EntityListeners(AuditingEntityListener.class)
public class Tarea {
	
	@Id
	@GeneratedValue(generator = "UUID") // Usar el mismo valor que el parámetro name de @GenericGenerator
	@GenericGenerator(
	        name = "UUID", // Usar el mismo valor que el parámetro generator de @GeneratedValue
	        strategy = "org.hibernate.id.UUIDGenerator",
	        parameters = {
	                @Parameter(
	                        name = "uuid_gen_strategy_class",
	                        value = "org.hibernate.id.uuid.CustomVersionOneStrategy")
	        })
	@CreatedBy
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Set<EstadoTarea> estados;
	
	@ManyToOne(cascade = CascadeType.PERSIST) // Propagar la operación de persistencia al usuario
	@JoinColumn(name = "usuario_id") 
	private Usuario usuario;
	


	@CreatedDate
	private LocalDateTime creadoEn;
	
	
}
