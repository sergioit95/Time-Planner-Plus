package com.sergiogarcia.app.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.TareaService;
import com.sergiogarcia.app.servicios.ServicioUsuario;
/*
@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final TareaService tareaService;
    private final ServicioUsuario usuarioService;

    @Autowired
    public TareaController(TareaService tareaService, ServicioUsuario usuarioService) {
        this.tareaService = tareaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Tarea addTarea(@RequestBody Tarea tarea, @AuthenticationPrincipal Usuario usuario) {
        return tareaService.saveTarea(tarea, usuario);
    }

    @PutMapping
    public Tarea updateTarea(@RequestBody Tarea tarea, @AuthenticationPrincipal Usuario usuario) throws Exception {
        return tareaService.updateTarea(tarea, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteTarea(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) throws Exception {
        tareaService.deleteTarea(id, usuario);
    }
}*/
