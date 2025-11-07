package pe.edu.idat.soporte.soportebackend.service;

import pe.edu.idat.soporte.soportebackend.dto.SolicitudDTO;
import pe.edu.idat.soporte.soportebackend.model.Solicitud;

import java.util.List;

public interface IGenericService<T, D> {
    List<T> obtenerTodos();
    void registrar(D dto);
    T consultarPorId(Integer id);
    void actualizar(Integer id, D dto);
    void eliminar(Integer id);
}
