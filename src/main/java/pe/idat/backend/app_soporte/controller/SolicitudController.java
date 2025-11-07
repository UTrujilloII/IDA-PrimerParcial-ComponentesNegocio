package pe.idat.backend.app_soporte.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.backend.app_soporte.dto.SolicitudDTO;
import pe.idat.backend.app_soporte.model.Solicitud;
import pe.idat.backend.app_soporte.service.SolicitudService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    /**
     * Obtener todas las solicitudes
     * GET /api/solicitudes
     */
    @GetMapping
    public ResponseEntity<List<Solicitud>> obtenerTodas() {
        List<Solicitud> solicitudes = solicitudService.obtenerTodas();
        return ResponseEntity.ok(solicitudes);
    }

    /**
     * Obtener una solicitud por ID
     * GET /api/solicitudes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> obtenerPorId(@PathVariable Long id) {
        Solicitud solicitud = solicitudService.obtenerPorId(id);
        return ResponseEntity.ok(solicitud);
    }

    /**
     * Crear una nueva solicitud
     * POST /api/solicitudes
     */
    @PostMapping
    public ResponseEntity<Solicitud> crear(@Valid @RequestBody SolicitudDTO solicitudDTO) {
        Solicitud nuevaSolicitud = solicitudService.crearSolicitud(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSolicitud);
    }

    /**
     * Actualizar una solicitud existente
     * PUT /api/solicitudes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudDTO solicitudDTO) {
        Solicitud solicitudActualizada = solicitudService.actualizarSolicitud(id, solicitudDTO);
        return ResponseEntity.ok(solicitudActualizada);
    }

    /**
     * Eliminar una solicitud
     * DELETE /api/solicitudes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Solicitud eliminada exitosamente");

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Asignar un técnico a una solicitud
     * PATCH /api/solicitudes/{solicitudId}/asignar-tecnico/{tecnicoId}
     */
    @PatchMapping("/{solicitudId}/asignar-tecnico/{tecnicoId}")
    public ResponseEntity<Solicitud> asignarTecnico(
            @PathVariable Long solicitudId,
            @PathVariable Long tecnicoId) {
        Solicitud solicitudActualizada = solicitudService.asignarTecnico(solicitudId, tecnicoId);
        return ResponseEntity.ok(solicitudActualizada);
    }

    /**
     * Cambiar el estado de una solicitud
     * PATCH /api/solicitudes/{id}/estado
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Solicitud> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        Solicitud solicitudActualizada = solicitudService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(solicitudActualizada);
    }
}