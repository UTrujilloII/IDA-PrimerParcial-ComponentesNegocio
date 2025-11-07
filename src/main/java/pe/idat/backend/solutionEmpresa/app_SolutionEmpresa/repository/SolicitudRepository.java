package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Solicitud;

@Repository
public class SolicitudRepository {
    private final ConcurrentHashMap<Integer, Solicitud> almacenamiento = new ConcurrentHashMap();
    private final AtomicInteger contadorId = new AtomicInteger(0);

    public Solicitud guardar(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(this.contadorId.incrementAndGet());
        }

        this.almacenamiento.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    public Optional<Solicitud> buscarPorId(Integer id) {
        return Optional.ofNullable((Solicitud)this.almacenamiento.get(id));
    }

    public List<Solicitud> listarTodas() {
        return new ArrayList(this.almacenamiento.values());
    }

    public void eliminarPorId(Integer id) {
        this.almacenamiento.remove(id);
    }

    public boolean existePorId(Integer id) {
        return this.almacenamiento.containsKey(id);
    }

    public List<Solicitud> buscarPorClienteId(Integer clienteId) {
        return (List)this.almacenamiento.values().stream().filter((s) -> s.getClienteId() != null && s.getClienteId().equals(clienteId)).collect(Collectors.toList());
    }

    public List<Solicitud> buscarPorTecnicoId(Integer tecnicoId) {
        return (List)this.almacenamiento.values().stream().filter((s) -> s.getTecnicoId() != null && s.getTecnicoId().equals(tecnicoId)).collect(Collectors.toList());
    }
}
