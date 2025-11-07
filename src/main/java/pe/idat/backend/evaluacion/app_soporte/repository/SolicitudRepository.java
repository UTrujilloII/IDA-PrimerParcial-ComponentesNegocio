package pe.idat.backend.evaluacion.app_soporte.repository;

import pe.idat.backend.evaluacion.app_soporte.model.Solicitud;

import java.util.List;
import java.util.Optional;

public interface SolicitudRepository {

    Solicitud save(Solicitud solicitud);

    List<Solicitud> findAll();

    Optional<Solicitud> findById(Long id);

    Optional<Solicitud> update(Long id, Solicitud solicitud);

    boolean deleteById(Long id);
}
