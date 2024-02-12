package com.sergiogarcia.app.repositorios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;

public interface RepositorioTarea extends JpaRepository<Tarea, UUID> {

	//Busca el primer registro en la base de datos que coincida con el nombre de usuario dado
		Optional<Tarea> findFirstByTituloTarea(String titulo);
}
