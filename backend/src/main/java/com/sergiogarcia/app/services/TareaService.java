package com.sergiogarcia.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiogarcia.app.models.Tarea;
import com.sergiogarcia.app.models.Usuario;
import com.sergiogarcia.app.repositories.TareaRepository;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public Tarea saveTarea(Tarea tarea, Usuario usuario) {
        tarea.setUsuario(usuario);
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Tarea tarea, Usuario usuario) throws Exception {
        if (!tarea.getUsuario().equals(usuario)) {
            throw new Exception("No tienes permiso para editar esta tarea");
        }
        return tareaRepository.save(tarea);
    }

    public void deleteTarea(Long id, Usuario usuario) throws Exception {
        Tarea tarea = tareaRepository.findById(id).orElseThrow(() -> new Exception("Tarea no encontrada"));
        if (!tarea.getUsuario().equals(usuario)) {
            throw new Exception("No tienes permiso para eliminar esta tarea");
        }
        tareaRepository.delete(tarea);
    }
}
