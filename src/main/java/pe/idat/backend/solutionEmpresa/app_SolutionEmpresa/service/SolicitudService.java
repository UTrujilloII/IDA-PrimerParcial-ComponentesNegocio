package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service;

import java.util.List;
import java.util.Optional;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Solicitud;

public interface SolicitudService {
    Solicitud guardarSolicitud(Solicitud solicitud);

    List<Solicitud> listarTodasLasSolicitudes();

    Optional<Solicitud> buscarSolicitudPorId(Integer id);

    Solicitud actualizarSolicitud(Integer id, Solicitud solicitudActualizada);

    void eliminarSolicitudPorId(Integer id);

    List<Solicitud> buscarSolicitudesPorClienteId(Integer clienteId);

    List<Solicitud> buscarSolicitudesPorTecnicoId(Integer tecnicoId);

    Solicitud asignarTecnicoAIdentificacion(Integer solicitudId, Integer tecnicoId);
}