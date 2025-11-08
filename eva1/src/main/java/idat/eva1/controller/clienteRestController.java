package idat.eva1.controller;


import idat.eva1.dto.clienteDTO;
import idat.eva1.model.Cliente;
import idat.eva1.service.clienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class clienteRestController {

    //comunica con service
    private final clienteService clienteService;
    public clienteRestController(clienteService clienteService)
    {
        this.clienteService = clienteService;
    }

    //crear
    @PostMapping
    public ResponseEntity<clienteDTO> registrarCliente(@RequestBody @Valid clienteDTO dto) {
        clienteDTO nuevoCliente = clienteService.registrarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    //leer
    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente()
    {
        return ResponseEntity.ok(clienteService.listarCliente());
    }

    //busqueda por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }


    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<clienteDTO> actualizarCliente(@PathVariable Integer id, @RequestBody @Valid clienteDTO dto) {
        clienteDTO actualizado = clienteService.actualizarCliente(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}