package com.sergiogarcia.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiogarcia.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
