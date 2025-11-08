package idat.eva1.service;

import idat.eva1.exception.NotFoundException;
import idat.eva1.model.Solicitud;
import idat.eva1.repository.solicitudRepository;
import org.springframework.stereotype.Service;
import idat.eva1.dto.solicitudDTO;

import java.util.Date;
import java.util.List;

@Service
public class solicitudService {

    //comunica con repository
    private final solicitudRepository solicitudRepository;
    public solicitudService(solicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    //crea/registra
    public solicitudDTO registrarSolicitud(solicitudDTO dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setIdCliente(dto.getIdCliente());
        solicitud.setIdTecnico(dto.getIdTecnico());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setFechaRegistro(new Date());
        Solicitud nuevo = solicitudRepository.registrarSolicitud(solicitud);

        solicitudDTO respuesta = new solicitudDTO();
        respuesta.setIdCliente(nuevo.getIdCliente());
        respuesta.setIdTecnico(nuevo.getIdTecnico());
        respuesta.setDescripcion(nuevo.getDescripcion());
        return respuesta;
    }

    //lee
    public List<Solicitud> listarSolicitud() {
        return solicitudRepository.listarSolicitud();
    }

    //busca por id
    public Solicitud buscarSolicitudPorId(Integer id) {
        Solicitud solicitud = solicitudRepository.buscarSolicitudPorId(id);
        if (solicitud == null) {
            throw new NotFoundException("Error: Solicitud con ID " + id + " no encontrada.");
        }
        return solicitud;
    }

    //actualiza
    public solicitudDTO actualizarSolicitudPorId(Integer id, solicitudDTO dto) {
        Solicitud existe = solicitudRepository.buscarSolicitudPorId(id);

        if (existe == null) {
            throw new NotFoundException("Error: La solicitud " + id + " no existe.");
        }

        existe.setDescripcion(dto.getDescripcion());
        existe.setFechaRegistro(new Date());
        solicitudRepository.actualizarSolicitudPorId(id, existe);

        solicitudDTO actualizado = new solicitudDTO();
        actualizado.setIdCliente(existe.getIdCliente());
        actualizado.setIdTecnico(existe.getIdTecnico());
        actualizado.setDescripcion(existe.getDescripcion());
        return actualizado;
    }

    //elimina
    public void eliminarSolicitud(Integer id) {
        Solicitud existe = solicitudRepository.buscarSolicitudPorId(id);
        if (existe == null) {
            throw new NotFoundException("Error: Solicitud " + id + " no encontrada.");
        }
        solicitudRepository.eliminarSolicitud(id);
    }
}
