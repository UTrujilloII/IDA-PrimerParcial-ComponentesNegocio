package pe.edu.idat.soporte.soportebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.soporte.soportebackend.dto.TecnicoDTO;
import pe.edu.idat.soporte.soportebackend.model.Tecnico;
import pe.edu.idat.soporte.soportebackend.service.ITecnicoService;

import java.util.List;

@RestController
@RequestMapping("/api/tecnico")
@Tag(name="Tecnico",description = "operaciones del tecnico")
public class TecnicoController {

    private final ITecnicoService tecnicoService;

    public TecnicoController(ITecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> obtenerTecnicos() {
        return ResponseEntity.ok(tecnicoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> registrarTecnico(@Valid @RequestBody TecnicoDTO tecnico) {
        tecnicoService.registrar(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> consultarPorId(@PathVariable Integer id) {
        Tecnico tecnico = tecnicoService.consultarPorId(id);
        return ResponseEntity.ok(tecnico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTecnico(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDto) {
        tecnicoService.actualizar(id, tecnicoDto);
        return ResponseEntity.ok("Técnico actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTecnico(@PathVariable Integer id) {
        tecnicoService.eliminar(id);
        return ResponseEntity.ok("Técnico eliminado correctamente");
    }
}
