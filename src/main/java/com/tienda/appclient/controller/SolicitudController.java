package com.tienda.appclient.controller;

import com.tienda.appclient.dto.SolicitudDTO;
import com.tienda.appclient.dto.SuccessResponse;
import com.tienda.appclient.model.Solicitud;
import com.tienda.appclient.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes", description = "API para gestión de solicitudes de soporte técnico")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes")
    public ResponseEntity<List<SolicitudDTO>> listarTodas() {
        return ResponseEntity.ok(solicitudService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener solicitud por ID")
    public ResponseEntity<SolicitudDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitudService.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Obtener solicitud por código")
    public ResponseEntity<SolicitudDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(solicitudService.findByCodigo(codigo));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar solicitudes por estado")
    public ResponseEntity<List<SolicitudDTO>> listarPorEstado(@PathVariable Solicitud.EstadoSolicitud estado) {
        return ResponseEntity.ok(solicitudService.findByEstado(estado));
    }

    @GetMapping("/prioridad/{prioridad}")
    @Operation(summary = "Listar solicitudes por prioridad")
    public ResponseEntity<List<SolicitudDTO>> listarPorPrioridad(@PathVariable Solicitud.PrioridadSolicitud prioridad) {
        return ResponseEntity.ok(solicitudService.findByPrioridad(prioridad));
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar solicitudes de un cliente")
    public ResponseEntity<List<SolicitudDTO>> listarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(solicitudService.findByCliente(clienteId));
    }

    @GetMapping("/tecnico/{tecnicoId}")
    @Operation(summary = "Listar solicitudes asignadas a un técnico")
    public ResponseEntity<List<SolicitudDTO>> listarPorTecnico(@PathVariable Integer tecnicoId) {
        return ResponseEntity.ok(solicitudService.findByTecnico(tecnicoId));
    }

    @GetMapping("/sin-asignar")
    @Operation(summary = "Listar solicitudes sin asignar")
    public ResponseEntity<List<SolicitudDTO>> listarSinAsignar() {
        return ResponseEntity.ok(solicitudService.findSinAsignar());
    }

    @PostMapping
    @Operation(summary = "Crear nueva solicitud")
    public ResponseEntity<SolicitudDTO> crear(@Valid @RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO creada = solicitudService.create(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar solicitud")
    public ResponseEntity<SolicitudDTO> actualizar(@PathVariable Integer id,
                                                     @Valid @RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO actualizada = solicitudService.update(id, solicitudDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PatchMapping("/{id}/asignar/{tecnicoId}")
    @Operation(summary = "Asignar técnico a solicitud")
    public ResponseEntity<SolicitudDTO> asignarTecnico(@PathVariable Integer id,
                                                         @PathVariable Integer tecnicoId) {
        SolicitudDTO asignada = solicitudService.asignarTecnico(id, tecnicoId);
        return ResponseEntity.ok(asignada);
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de solicitud")
    public ResponseEntity<SolicitudDTO> cambiarEstado(@PathVariable Integer id,
                                                        @RequestParam Solicitud.EstadoSolicitud estado) {
        SolicitudDTO actualizada = solicitudService.cambiarEstado(id, estado);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar solicitud")
    public ResponseEntity<SuccessResponse> eliminar(@PathVariable Integer id) {
        solicitudService.delete(id);
        SuccessResponse response = new SuccessResponse("Solicitud eliminada exitosamente");
        return ResponseEntity.ok(response);
    }
}

