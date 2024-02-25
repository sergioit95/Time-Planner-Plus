package com.sergiogarcia.app.controladores;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sergiogarcia.app.dto.CrearSolicitudDeTarea;
import com.sergiogarcia.app.dto.RespuestaTarea;
import com.sergiogarcia.app.dto.RespuestaUsuario;
import com.sergiogarcia.app.modelos.Tarea;
import com.sergiogarcia.app.modelos.Usuario;
import com.sergiogarcia.app.servicios.ServicioTarea;
import com.sergiogarcia.app.servicios.ServicioUsuario;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class ControladorTarea {
    
    private final ServicioTarea servicioTarea;

   

    // Listar todas las tareas
    @GetMapping
    public ResponseEntity<List<Tarea>> listarTareas() {
        List<Tarea> tareas = servicioTarea.listarTareas();
        return ResponseEntity.ok(tareas);
    }

    // Buscar tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarTareaPorId(@PathVariable("id") UUID id) {
        Optional<Tarea> tarea = servicioTarea.buscarPorIdTarea(id);
        return tarea.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear tarea
    @PostMapping
    public ResponseEntity<RespuestaTarea> crearTarea(@RequestBody CrearSolicitudDeTarea crearSolicitudDeTarea) {
        try {
            Tarea tarea = servicioTarea.crearTarea(crearSolicitudDeTarea);
            return ResponseEntity.status(HttpStatus.CREATED).body(RespuestaTarea.deTarea(tarea));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Completar tarea
    @PostMapping("/{id}/completar")
    public ResponseEntity<Tarea> completarTarea(@PathVariable("id") UUID id) {
        try {
            Tarea tarea = servicioTarea.completarTarea(id);
            return ResponseEntity.ok(tarea);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Desmarcar tarea como no completada
    @PostMapping("/{id}/desmarcar")
    public ResponseEntity<Tarea> desmarcarTarea(@PathVariable("id") UUID id) {
        try {
            Tarea tarea = servicioTarea.desmarcarTarea(id);
            return ResponseEntity.ok(tarea);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Editar tarea
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> editarTarea(@PathVariable("id") UUID id, @RequestBody Tarea tarea) {
        tarea.setId(id);
        Optional<Tarea> tareaActualizada = servicioTarea.editarTarea(tarea);
        return tareaActualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTareaPorId(@PathVariable("id") UUID id) throws Exception {
        servicioTarea.eliminarTareaPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener tareas completadas
    @GetMapping("/completadas")
    public ResponseEntity<List<Tarea>> obtenerTareasCompletadas() {
        List<Tarea> tareas = servicioTarea.obtenerTareasCompletadas();
        return ResponseEntity.ok(tareas);
    }

    // Obtener tareas no completadas
    @GetMapping("/no-completadas")
    public ResponseEntity<List<Tarea>> obtenerTareasNoCompletadas() {
        List<Tarea> tareas = servicioTarea.obtenerTareasNoCompletadas();
        return ResponseEntity.ok(tareas);
    }
}
