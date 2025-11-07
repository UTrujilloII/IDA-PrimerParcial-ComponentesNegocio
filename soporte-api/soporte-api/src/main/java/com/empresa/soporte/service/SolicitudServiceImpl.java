package com.empresa.soporte.service;

import com.empresa.soporte.exception.RecursoNoEncontradoException;
import com.empresa.soporte.model.Solicitud;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    // Lista estática
    private static final List<Solicitud> lista = new ArrayList<>();
    private static Long contador = 1L;

    // Lista
    @Override
    public List<Solicitud> listar() {
        return new ArrayList<>(lista);
    }

    // Buscar
    @Override
    public Solicitud buscarPorId(Long id) {
        return lista.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud con ID " + id + " no encontrada"));
    }

    // Registro
    @Override
    public Solicitud registrar(Solicitud solicitud) {
        solicitud.setId(contador++);
        lista.add(solicitud);
        return solicitud;
    }

    // Actualizar
    @Override
    public Solicitud actualizar(Long id, Solicitud solicitud) {
        Solicitud existente = buscarPorId(id);
        existente.setTitulo(solicitud.getTitulo());
        existente.setDescripcion(solicitud.getDescripcion());
        existente.setCliente(solicitud.getCliente());
        existente.setTecnico(solicitud.getTecnico());
        existente.setEstado(solicitud.getEstado());
        existente.setFechaRegistro(solicitud.getFechaRegistro());
        return existente;
    }

    // Eliminar
    @Override
    public void eliminar(Long id) {
        lista.removeIf(s -> s.getId().equals(id));
    }
}
