package pe.idat.backend.evaluacion.app_soporte.repository;

import org.springframework.stereotype.Repository;
import pe.idat.backend.evaluacion.app_soporte.model.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepositoryImpl implements SolicitudRepository {

    private final Map<Long, Solicitud> solicitudesEnMemoria = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(0);


    @Override
    public Solicitud save(Solicitud solicitud) {
        // La generación de ID ahora es responsabilidad del repositorio
        Long nuevoId = counter.incrementAndGet();
        solicitud.setId(nuevoId);

        solicitudesEnMemoria.put(nuevoId, solicitud);
        return solicitud;
    }

    @Override
    public List<Solicitud> findAll() {
        return new ArrayList<>(solicitudesEnMemoria.values());
    }

    @Override
    public Optional<Solicitud> findById(Long id) {
        return Optional.ofNullable(solicitudesEnMemoria.get(id));
    }

    @Override
    public Optional<Solicitud> update(Long id, Solicitud solicitud) {
        if (!solicitudesEnMemoria.containsKey(id)) {
            return Optional.empty();
        }

        solicitud.setId(id); // Aseguramos el ID
        solicitudesEnMemoria.put(id, solicitud);
        return Optional.of(solicitud);
    }

    @Override
    public boolean deleteById(Long id) {
        return solicitudesEnMemoria.remove(id) != null;
    }
}