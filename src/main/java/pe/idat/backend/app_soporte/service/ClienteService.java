package pe.idat.backend.app_soporte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.idat.backend.app_soporte.exception.ResourceNotFoundException;
import pe.idat.backend.app_soporte.model.Cliente;
import pe.idat.backend.app_soporte.repository.SolicitudRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final SolicitudRepository repository;

    public Cliente crearCliente(Cliente cliente) {
        return repository.guardarCliente(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return repository.obtenerTodosClientes();
    }

    public Cliente obtenerPorId(Long id) {
        Cliente cliente = repository.obtenerClientePorId(id);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente", id);
        }
        return cliente;
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = obtenerPorId(id);

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setEmpresa(cliente.getEmpresa());

        return repository.guardarCliente(clienteExistente);
    }

    public void eliminarCliente(Long id) {
        if (!repository.existeClientePorId(id)) {
            throw new ResourceNotFoundException("Cliente", id);
        }
        repository.eliminarCliente(id);
    }
}