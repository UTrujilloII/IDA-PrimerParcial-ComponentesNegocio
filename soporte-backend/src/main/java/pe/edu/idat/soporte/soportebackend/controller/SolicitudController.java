package pe.edu.idat.soporte.soportebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.soporte.soportebackend.dto.SolicitudDTO;
import pe.edu.idat.soporte.soportebackend.model.Solicitud;
import pe.edu.idat.soporte.soportebackend.service.ISolicitudService;
import java.util.List;


@RestController
@RequestMapping("/api/solicitud")
@Tag(name="Solicitud",description = "operaciones de las solicitudes")
public class SolicitudController{

    private final ISolicitudService ISolicitudService;

    public SolicitudController(ISolicitudService ISolicitudService){
        this.ISolicitudService=ISolicitudService;
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> obtenerSolicitudes(){
        return ResponseEntity.ok(this.ISolicitudService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> registrarSolicitud(@Valid @RequestBody SolicitudDTO solicitud){
        this.ISolicitudService.registrar(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> consultarPorId(@PathVariable int id){
        Solicitud solicitud=this.ISolicitudService.consultarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(solicitud);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarSolicitud( @PathVariable Integer id, @Valid @RequestBody SolicitudDTO solicitudDto){
        this.ISolicitudService.actualizar(id,solicitudDto);
        return ResponseEntity.ok("se actualizo correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarSolicitud(@PathVariable Integer id){
        this.ISolicitudService.eliminar(id);
        return ResponseEntity.ok("solicitud eliminada ");
    }

}
