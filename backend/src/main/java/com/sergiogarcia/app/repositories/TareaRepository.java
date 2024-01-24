package com.sergiogarcia.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiogarcia.app.models.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
