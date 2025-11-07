package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.BadRequestException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.ResourceNotFoundException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Cliente;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository.ClienteRepository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new BadRequestException("El cliente no puede ser nulo.");
        } else if (cliente.getNombre() != null && !cliente.getNombre().trim().isEmpty()) {
            if (cliente.getCorreo() != null && !cliente.getCorreo().trim().isEmpty()) {
                String correo = cliente.getCorreo().trim();
                if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    throw new BadRequestException("El correo del cliente no tiene un formato válido.");
                }

                cliente.setCorreo(correo);
            }

            return this.clienteRepository.guardar(cliente);
        } else {
            throw new BadRequestException("El nombre del cliente es obligatorio.");
        }
    }

    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        Cliente existente = (Cliente)this.clienteRepository.buscarPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        if (clienteActualizado.getNombre() != null && !clienteActualizado.getNombre().trim().isEmpty()) {
            if (clienteActualizado.getCorreo() != null && !clienteActualizado.getCorreo().trim().isEmpty()) {
                existente.setNombre(clienteActualizado.getNombre());
                existente.setCorreo(clienteActualizado.getCorreo());
                return this.clienteRepository.guardar(existente);
            } else {
                throw new BadRequestException("El correo es obligatorio.");
            }
        } else {
            throw new BadRequestException("El nombre es obligatorio.");
        }
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del cliente no puede ser nulo al buscar.");
        } else {
            return this.clienteRepository.buscarPorId(id);
        }
    }

    public Cliente obtenerClientePorIdOError(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del cliente no puede ser nulo.");
        } else {
            return (Cliente)this.clienteRepository.buscarPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        }
    }

    public List<Cliente> listarClientes() {
        return this.clienteRepository.listarTodos();
    }

    public void eliminarClientePorId(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del cliente no puede ser nulo para eliminar.");
        } else if (!this.clienteRepository.existePorId(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: cliente no encontrado con ID " + id);
        } else {
            this.clienteRepository.eliminarPorId(id);
        }
    }

    public boolean existeClientePorId(Integer id) {
        return id == null ? false : this.clienteRepository.existePorId(id);
    }
}
