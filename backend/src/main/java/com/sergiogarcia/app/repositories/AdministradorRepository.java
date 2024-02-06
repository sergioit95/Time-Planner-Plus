package com.sergiogarcia.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiogarcia.app.models.Administrador;
import com.sergiogarcia.app.models.Usuario;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

}
