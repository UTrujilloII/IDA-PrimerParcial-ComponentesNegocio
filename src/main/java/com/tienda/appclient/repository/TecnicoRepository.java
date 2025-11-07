package com.tienda.appclient.repository;

import com.tienda.appclient.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    Optional<Tecnico> findByCodigo(String codigo);

    Optional<Tecnico> findByEmail(String email);

    List<Tecnico> findByActivo(Boolean activo);

    List<Tecnico> findByEspecialidad(String especialidad);

    boolean existsByCodigo(String codigo);

    boolean existsByEmail(String email);
}

