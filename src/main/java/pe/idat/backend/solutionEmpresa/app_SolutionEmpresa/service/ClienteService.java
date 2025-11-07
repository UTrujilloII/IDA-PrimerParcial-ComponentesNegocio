package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service;

import java.util.List;
import java.util.Optional;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Cliente;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente);

    Optional<Cliente> buscarClientePorId(Integer id);

    List<Cliente> listarClientes();

    Cliente actualizarCliente(Integer id, Cliente clienteActualizado);

    void eliminarClientePorId(Integer id);

    boolean existeClientePorId(Integer id);

    Cliente obtenerClientePorIdOError(Integer id);
}
