package idat.eva1.controller;

import idat.eva1.dto.tecnicoDTO;
import idat.eva1.model.Tecnico;
import idat.eva1.service.tecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class tecnicoRestController {

    //comunica con service
    private final tecnicoService tecnicoService;
    public tecnicoRestController(tecnicoService tecnicoService)
    {
        this.tecnicoService = tecnicoService;
    }

    //crear
    @PostMapping
    public ResponseEntity<tecnicoDTO> registrarTecnico(@RequestBody @Valid tecnicoDTO dto) {
        tecnicoDTO nuevo = tecnicoService.registrarTecnico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    //leer
    @GetMapping
    public ResponseEntity<List<Tecnico>> listarTecnico() {
        return ResponseEntity.ok(tecnicoService.listarTecnico());
    }

    //busqueda por id
    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarTecnicoPorId(@PathVariable Integer id)
    {
        Tecnico tecnico = tecnicoService.buscarTecnicoPorId(id);
        return ResponseEntity.ok(tecnico);
    }

    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<tecnicoDTO> actualizarTecnico(@PathVariable Integer id, @RequestBody tecnicoDTO dto) {
        tecnicoDTO actualizado = tecnicoService.actualizarTecnico(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTecnico(@PathVariable Integer id) {
        tecnicoService.eliminarTecnico(id);
        return ResponseEntity.noContent().build();
    }
}