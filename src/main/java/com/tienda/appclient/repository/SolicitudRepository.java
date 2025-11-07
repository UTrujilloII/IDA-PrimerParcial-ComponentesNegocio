package com.tienda.appclient.repository;

import com.tienda.appclient.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    Optional<Solicitud> findByCodigo(String codigo);

    List<Solicitud> findByEstado(Solicitud.EstadoSolicitud estado);

    List<Solicitud> findByPrioridad(Solicitud.PrioridadSolicitud prioridad);

    List<Solicitud> findByClienteId(Integer clienteId);

    List<Solicitud> findByTecnicoId(Integer tecnicoId);

    List<Solicitud> findByTecnicoIsNull();

    boolean existsByCodigo(String codigo);
}

