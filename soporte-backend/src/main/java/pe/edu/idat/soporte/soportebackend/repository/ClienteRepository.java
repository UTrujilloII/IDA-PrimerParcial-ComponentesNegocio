package pe.edu.idat.soporte.soportebackend.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.soporte.soportebackend.model.Cliente;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ClienteRepository implements IClienteRepository{

    private final Map<Integer, Cliente> bdCliente = new HashMap<>();
    private final AtomicInteger secuencial = new AtomicInteger(0);

    @Override
    public List<Cliente> buscarTodo() {
        return new ArrayList<>(bdCliente.values());
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(secuencial.incrementAndGet());
        }
        bdCliente.put(cliente.getId(), cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        return Optional.ofNullable(bdCliente.get(id));
    }

    @Override
    public void actualizar(Cliente cliente) {
        if (bdCliente.containsKey(cliente.getId())) {
            bdCliente.put(cliente.getId(), cliente);
        }
    }

    @Override
    public void eliminar(Integer id) {
        bdCliente.remove(id);
    }
}
