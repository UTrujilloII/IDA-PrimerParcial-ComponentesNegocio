package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.dto.TecnicoDto;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.BadRequestException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.ResourceNotFoundException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Tecnico;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.TecnicoService;

@RestController
@RequestMapping({"/api/tecnicos"})
public class TecnicoController {
    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    public ResponseEntity<String> guardarTecnico(@RequestBody @Valid Tecnico tecnico) {
        Tecnico guardado = this.tecnicoService.guardarTecnico(tecnico);
        return ResponseEntity.ok("Técnico creado con éxito con ID: " + guardado.getId());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TecnicoDto> obtenerTecnicoPorId(@PathVariable Integer id) {
        Tecnico tecnico = (Tecnico)this.tecnicoService.buscarTecnicoPorId(id).orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado"));
        TecnicoDto dto = new TecnicoDto(tecnico.getId(), tecnico.getNombre(), tecnico.getEspecialidad());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> listarTodosLosTecnicos() {
        List<TecnicoDto> lista = (List)this.tecnicoService.listarTecnicos().stream().map((t) -> new TecnicoDto(t.getId(), t.getNombre(), t.getEspecialidad())).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<String> actualizarTecnico(@PathVariable Integer id, @RequestBody @Valid Tecnico tecnicoActualizado) {
        if (id == null) {
            throw new BadRequestException("El ID del técnico no puede ser nulo.");
        } else {
            Tecnico existente = (Tecnico)this.tecnicoService.buscarTecnicoPorId(id).orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + id));
            if (tecnicoActualizado.getNombre() != null) {
                existente.setNombre(tecnicoActualizado.getNombre());
            }

            if (tecnicoActualizado.getEspecialidad() != null) {
                existente.setEspecialidad(tecnicoActualizado.getEspecialidad());
            }

            this.tecnicoService.guardarTecnico(existente);
            return ResponseEntity.ok("Técnico actualizado con éxito con ID: " + existente.getId());
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarTecnico(@PathVariable Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del técnico no puede ser nulo.");
        } else if (!this.tecnicoService.existeTecnicoPorId(id)) {
            throw new ResourceNotFoundException("Técnico no encontrado con ID: " + id);
        } else {
            this.tecnicoService.eliminarTecnicoPorId(id);
            return ResponseEntity.ok("Técnico eliminado con éxito con ID: " + id);
        }
    }
}
