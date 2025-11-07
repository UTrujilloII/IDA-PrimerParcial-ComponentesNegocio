package pe.edu.idat.soporte.soportebackend.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.soporte.soportebackend.dto.ClienteDTO;
import pe.edu.idat.soporte.soportebackend.exceptions.RecursoNoEncontradoException;
import pe.edu.idat.soporte.soportebackend.model.Cliente;
import pe.edu.idat.soporte.soportebackend.repository.ClienteRepository;

import java.util.List;


@Service
public class ClienteService implements IClienteService{
    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return this.clienteRepo.buscarTodo();
    }

    @Override
    public void registrar(ClienteDTO dto) {
        Cliente nuevoCliente = Cliente.builder()
                .dni(dto.getDni())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
        this.clienteRepo.guardar(nuevoCliente);
    }

    @Override
    public Cliente consultarPorId(Integer id) {
        return this.clienteRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con el id: " + id));
    }

    @Override
    public void actualizar(Integer id, ClienteDTO dto) {
        Cliente existeCliente = this.clienteRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con el id: " + id));

        existeCliente.setDni(dto.getDni());
        existeCliente.setNombre(dto.getNombre());
        existeCliente.setApellido(dto.getApellido());
        existeCliente.setEmail(dto.getEmail());
        existeCliente.setTelefono(dto.getTelefono());

        this.clienteRepo.actualizar(existeCliente);
    }

    @Override
    public void eliminar(Integer id) {
        this.clienteRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con el id: " + id));
        this.clienteRepo.eliminar(id);
    }
}
