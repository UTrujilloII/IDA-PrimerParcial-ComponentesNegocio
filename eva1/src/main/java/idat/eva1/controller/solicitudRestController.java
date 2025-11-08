package idat.eva1.controller;

import idat.eva1.dto.solicitudDTO;

import idat.eva1.model.Solicitud;
import idat.eva1.service.solicitudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class solicitudRestController {

    //comunicacion con service
    private final solicitudService solicitudService;
    public solicitudRestController(solicitudService solicitudService)
    {
        this.solicitudService = solicitudService;
    }

    //crea
    @PostMapping
    public ResponseEntity<solicitudDTO> registrarSolicitud(@RequestBody @Valid solicitudDTO dto) {
        solicitudDTO nueva = solicitudService.registrarSolicitud(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    //lee
    @GetMapping
    public ResponseEntity<List<Solicitud>> listarSolicitud() {
        return ResponseEntity.ok(solicitudService.listarSolicitud());
    }

    //busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> buscarSolicitudPorId(@PathVariable Integer id) {
        Solicitud solicitud = solicitudService.buscarSolicitudPorId(id);
        return ResponseEntity.ok(solicitud);
    }

    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<solicitudDTO> actualizarSolicitudPorId(@PathVariable Integer id, @RequestBody solicitudDTO dto)
    {
        solicitudDTO actualizado = solicitudService.actualizarSolicitudPorId(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Integer id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
