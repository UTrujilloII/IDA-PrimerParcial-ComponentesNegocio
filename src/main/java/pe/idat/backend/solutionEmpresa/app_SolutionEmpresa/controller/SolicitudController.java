package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.dto.SolicitudDto;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Solicitud;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.SolicitudService;

@RestController
@RequestMapping({"/api/solicitudes"})
public class SolicitudController {
    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<String> crearSolicitud(@Validated({SolicitudDto.Crear.class, Default.class}) @RequestBody SolicitudDto dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setEstado(dto.getEstado());
        solicitud.setClienteId(dto.getClienteId());
        solicitud.setTecnicoId(dto.getTecnicoId());
        solicitud.setFechaRegistro(LocalDateTime.now());
        Solicitud guardada = this.solicitudService.guardarSolicitud(solicitud);
        return ResponseEntity.ok("Solicitud creada con éxito con ID: " + guardada.getId());
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDto>> listarSolicitudes() {
        List<SolicitudDto> lista = (List)this.solicitudService.listarTodasLasSolicitudes().stream().map(this::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<SolicitudDto> obtenerSolicitudPorId(@PathVariable Integer id) {
        Solicitud solicitud = (Solicitud)this.solicitudService.buscarSolicitudPorId(id).orElseThrow(() -> new RuntimeException("Solicitud con id " + id + " no encontrada"));
        return ResponseEntity.ok(this.mapToDto(solicitud));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<String> actualizarSolicitud(@PathVariable Integer id, @RequestBody @Valid SolicitudDto dto) {
        Solicitud solicitudActualizada = new Solicitud();
        solicitudActualizada.setDescripcion(dto.getDescripcion());
        solicitudActualizada.setEstado(dto.getEstado());
        solicitudActualizada.setTecnicoId(dto.getTecnicoId());
        Solicitud actualizada = this.solicitudService.actualizarSolicitud(id, solicitudActualizada);
        return ResponseEntity.ok("Solicitud actualizada con éxito con ID: " + actualizada.getId());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarSolicitud(@PathVariable Integer id) {
        this.solicitudService.eliminarSolicitudPorId(id);
        return ResponseEntity.ok("Solicitud eliminada con éxito con ID: " + id);
    }

    private SolicitudDto mapToDto(Solicitud solicitud) {
        return new SolicitudDto(solicitud.getId(), solicitud.getDescripcion(), solicitud.getFechaRegistro(), solicitud.getEstado(), solicitud.getClienteId(), solicitud.getTecnicoId());
    }
}
