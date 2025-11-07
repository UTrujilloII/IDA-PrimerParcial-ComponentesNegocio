package pe.idat.backend.app_soporte.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.backend.app_soporte.model.Tecnico;
import pe.idat.backend.app_soporte.service.TecnicoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    private final TecnicoService tecnicoService;

    /**
     * Obtener todos los técnicos
     * GET /api/tecnicos
     */
    @GetMapping
    public ResponseEntity<List<Tecnico>> obtenerTodos() {
        List<Tecnico> tecnicos = tecnicoService.obtenerTodos();
        return ResponseEntity.ok(tecnicos);
    }

    /**
     * Obtener un técnico por ID
     * GET /api/tecnicos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> obtenerPorId(@PathVariable Long id) {
        Tecnico tecnico = tecnicoService.obtenerPorId(id);
        return ResponseEntity.ok(tecnico);
    }

    /**
     * Crear un nuevo técnico
     * POST /api/tecnicos
     */
    @PostMapping
    public ResponseEntity<Tecnico> crear(@Valid @RequestBody Tecnico tecnico) {
        Tecnico nuevoTecnico = tecnicoService.crearTecnico(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTecnico);
    }

    /**
     * Actualizar un técnico existente
     * PUT /api/tecnicos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Tecnico tecnico) {
        Tecnico tecnicoActualizado = tecnicoService.actualizarTecnico(id, tecnico);
        return ResponseEntity.ok(tecnicoActualizado);
    }

    /**
     * Eliminar un técnico
     * DELETE /api/tecnicos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        tecnicoService.eliminarTecnico(id);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Técnico eliminado exitosamente");

        return ResponseEntity.ok(respuesta);
    }
}