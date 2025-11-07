package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Tecnico;

@Repository
public class TecnicoRepository {
    private final ConcurrentHashMap<Integer, Tecnico> almacenamiento = new ConcurrentHashMap();
    private final AtomicInteger contadorId = new AtomicInteger(0);

    public Tecnico guardar(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            tecnico.setId(this.contadorId.incrementAndGet());
        }

        this.almacenamiento.put(tecnico.getId(), tecnico);
        return tecnico;
    }

    public Optional<Tecnico> buscarPorId(Integer id) {
        return Optional.ofNullable((Tecnico)this.almacenamiento.get(id));
    }

    public List<Tecnico> listarTodos() {
        return new ArrayList(this.almacenamiento.values());
    }

    public void eliminarPorId(Integer id) {
        this.almacenamiento.remove(id);
    }

    public boolean existePorId(Integer id) {
        return this.almacenamiento.containsKey(id);
    }
}
