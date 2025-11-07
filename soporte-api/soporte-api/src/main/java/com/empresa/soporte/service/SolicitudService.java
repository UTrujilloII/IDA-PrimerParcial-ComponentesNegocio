package com.empresa.soporte.service;

import com.empresa.soporte.model.Solicitud;
import java.util.List;

public interface SolicitudService {
    List<Solicitud> listar();
    Solicitud buscarPorId(Long id);
    Solicitud registrar(Solicitud solicitud);
    Solicitud actualizar(Long id, Solicitud solicitud);
    void eliminar(Long id);
}
