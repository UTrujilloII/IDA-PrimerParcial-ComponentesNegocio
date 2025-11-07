package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Cliente;

@Repository
public class ClienteRepository {
    private final ConcurrentHashMap<Integer, Cliente> almacenamiento = new ConcurrentHashMap();
    private final AtomicInteger contadorId = new AtomicInteger(0);

    public Cliente guardar(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(this.contadorId.incrementAndGet());
        }

        this.almacenamiento.put(cliente.getId(), cliente);
        return cliente;
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return Optional.ofNullable((Cliente)this.almacenamiento.get(id));
    }

    public List<Cliente> listarTodos() {
        return new ArrayList(this.almacenamiento.values());
    }

    public void eliminarPorId(Integer id) {
        this.almacenamiento.remove(id);
    }

    public boolean existePorId(Integer id) {
        return this.almacenamiento.containsKey(id);
    }
}
