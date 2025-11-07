package pe.idat.backend.evaluacion.app_soporte.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudRequestDto;
import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudResponseDto;
import pe.idat.backend.evaluacion.app_soporte.exeption.ResourceNotFoundException;
import pe.idat.backend.evaluacion.app_soporte.service.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<SolicitudResponseDto> crearSolicitud(@Valid @RequestBody SolicitudRequestDto requestDto) {
        SolicitudResponseDto responseDto = solicitudService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<SolicitudResponseDto>> listarSolicitudes() {
        List<SolicitudResponseDto> responseDtos = solicitudService.findAll();
        return ResponseEntity.ok(responseDtos); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDto> obtenerSolicitudPorId(@PathVariable Long id) {
        SolicitudResponseDto responseDto = solicitudService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + id));
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudResponseDto> actualizarSolicitud(@PathVariable Long id, @Valid @RequestBody SolicitudRequestDto requestDto) {
        SolicitudResponseDto responseDto = solicitudService.update(id, requestDto)
                .orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar. Solicitud no encontrada con ID: " + id));
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        if (!solicitudService.deleteById(id)) {
            throw new ResourceNotFoundException("No se pudo eliminar. Solicitud no encontrada con ID: " + id);
        }

        return ResponseEntity.noContent().build();
    }
}