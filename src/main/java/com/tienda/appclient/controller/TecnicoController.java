package com.tienda.appclient.controller;

import com.tienda.appclient.dto.SuccessResponse;
import com.tienda.appclient.dto.TecnicoDTO;
import com.tienda.appclient.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@Tag(name = "Técnicos", description = "API para gestión de técnicos de soporte")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    @Operation(summary = "Listar todos los técnicos")
    public ResponseEntity<List<TecnicoDTO>> listarTodos() {
        return ResponseEntity.ok(tecnicoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener técnico por ID")
    public ResponseEntity<TecnicoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tecnicoService.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Obtener técnico por código")
    public ResponseEntity<TecnicoDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(tecnicoService.findByCodigo(codigo));
    }

    @GetMapping("/activos")
    @Operation(summary = "Listar técnicos activos")
    public ResponseEntity<List<TecnicoDTO>> listarActivos() {
        return ResponseEntity.ok(tecnicoService.findByActivo(true));
    }

    @GetMapping("/especialidad/{especialidad}")
    @Operation(summary = "Buscar técnicos por especialidad")
    public ResponseEntity<List<TecnicoDTO>> buscarPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(tecnicoService.findByEspecialidad(especialidad));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo técnico")
    public ResponseEntity<TecnicoDTO> crear(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO creado = tecnicoService.create(tecnicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar técnico")
    public ResponseEntity<TecnicoDTO> actualizar(@PathVariable Integer id,
                                                   @Valid @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO actualizado = tecnicoService.update(id, tecnicoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar técnico")
    public ResponseEntity<SuccessResponse> eliminar(@PathVariable Integer id) {
        tecnicoService.delete(id);
        SuccessResponse response = new SuccessResponse("Técnico eliminado exitosamente");
        return ResponseEntity.ok(response);
    }
}

