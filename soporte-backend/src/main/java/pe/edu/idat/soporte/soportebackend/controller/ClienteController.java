package pe.edu.idat.soporte.soportebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.soporte.soportebackend.dto.ClienteDTO;
import pe.edu.idat.soporte.soportebackend.model.Cliente;
import pe.edu.idat.soporte.soportebackend.service.IClienteService;


import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@Tag(name="Cliente",description = "operaciones del cliente")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> registrarCliente(@Valid @RequestBody ClienteDTO cliente) {
        clienteService.registrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.consultarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDto) {
        clienteService.actualizar(id, clienteDto);
        return ResponseEntity.ok("Cliente actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
}