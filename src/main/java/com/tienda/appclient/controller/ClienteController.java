package com.tienda.appclient.controller;

import com.tienda.appclient.dto.ClienteDTO;
import com.tienda.appclient.dto.SuccessResponse;
import com.tienda.appclient.mapper.ClienteMapper;
import com.tienda.appclient.model.Cliente;
import com.tienda.appclient.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listarTodos().stream().map(ClienteMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(ClienteMapper.toDto(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO clienteDto) {
        var entity = ClienteMapper.toEntity(clienteDto);
        var creado = clienteService.crear(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDto(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDto) {
        var entity = ClienteMapper.toEntity(clienteDto);
        var actualizado = clienteService.actualizar(id, entity);
        return ResponseEntity.ok(ClienteMapper.toDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        SuccessResponse response = new SuccessResponse("Cliente eliminado exitosamente");
        return ResponseEntity.ok(response);
    }
}
