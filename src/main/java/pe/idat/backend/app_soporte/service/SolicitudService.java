package pe.idat.backend.app_soporte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.idat.backend.app_soporte.dto.SolicitudDTO;
import pe.idat.backend.app_soporte.exception.ResourceNotFoundException;
import pe.idat.backend.app_soporte.model.Cliente;
import pe.idat.backend.app_soporte.model.Solicitud;
import pe.idat.backend.app_soporte.model.Tecnico;
import pe.idat.backend.app_soporte.repository.SolicitudRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository repository;

    public Solicitud crearSolicitud(SolicitudDTO solicitudDTO) {
        // Validar que el cliente existe
        Cliente cliente = repository.obtenerClientePorId(solicitudDTO.getClienteId());
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente", solicitudDTO.getClienteId());
        }

        // Crear la solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setTitulo(solicitudDTO.getTitulo());
        solicitud.setDescripcion(solicitudDTO.getDescripcion());
        solicitud.setCliente(cliente);
        solicitud.setPrioridad(solicitudDTO.getPrioridad());
        solicitud.setEstado(solicitudDTO.getEstado());
        solicitud.setFechaCreacion(LocalDateTime.now());
        solicitud.setFechaActualizacion(LocalDateTime.now());

        // Asignar técnico si se proporcionó
        if (solicitudDTO.getTecnicoId() != null) {
            Tecnico tecnico = repository.obtenerTecnicoPorId(solicitudDTO.getTecnicoId());
            if (tecnico == null) {
                throw new ResourceNotFoundException("Técnico", solicitudDTO.getTecnicoId());
            }
            solicitud.setTecnicoAsignado(tecnico);
        }

        return repository.guardar(solicitud);
    }

    public List<Solicitud> obtenerTodas() {
        return repository.obtenerTodas();
    }

    public Solicitud obtenerPorId(Long id) {
        Solicitud solicitud = repository.obtenerPorId(id);
        if (solicitud == null) {
            throw new ResourceNotFoundException("Solicitud", id);
        }
        return solicitud;
    }

    public Solicitud actualizarSolicitud(Long id, SolicitudDTO solicitudDTO) {
        Solicitud solicitudExistente = obtenerPorId(id);

        // Actualizar campos
        solicitudExistente.setTitulo(solicitudDTO.getTitulo());
        solicitudExistente.setDescripcion(solicitudDTO.getDescripcion());
        solicitudExistente.setPrioridad(solicitudDTO.getPrioridad());
        solicitudExistente.setEstado(solicitudDTO.getEstado());
        solicitudExistente.setFechaActualizacion(LocalDateTime.now());

        // Actualizar cliente si cambió
        if (!solicitudExistente.getCliente().getId().equals(solicitudDTO.getClienteId())) {
            Cliente nuevoCliente = repository.obtenerClientePorId(solicitudDTO.getClienteId());
            if (nuevoCliente == null) {
                throw new ResourceNotFoundException("Cliente", solicitudDTO.getClienteId());
            }
            solicitudExistente.setCliente(nuevoCliente);
        }

        // Actualizar técnico si cambió
        if (solicitudDTO.getTecnicoId() != null) {
            Tecnico nuevoTecnico = repository.obtenerTecnicoPorId(solicitudDTO.getTecnicoId());
            if (nuevoTecnico == null) {
                throw new ResourceNotFoundException("Técnico", solicitudDTO.getTecnicoId());
            }
            solicitudExistente.setTecnicoAsignado(nuevoTecnico);
        } else {
            solicitudExistente.setTecnicoAsignado(null);
        }

        return repository.guardar(solicitudExistente);
    }

    public void eliminarSolicitud(Long id) {
        if (!repository.existePorId(id)) {
            throw new ResourceNotFoundException("Solicitud", id);
        }
        repository.eliminar(id);
    }

    public Solicitud asignarTecnico(Long solicitudId, Long tecnicoId) {
        Solicitud solicitud = obtenerPorId(solicitudId);
        Tecnico tecnico = repository.obtenerTecnicoPorId(tecnicoId);

        if (tecnico == null) {
            throw new ResourceNotFoundException("Técnico", tecnicoId);
        }

        solicitud.setTecnicoAsignado(tecnico);
        solicitud.setFechaActualizacion(LocalDateTime.now());

        return repository.guardar(solicitud);
    }

    public Solicitud cambiarEstado(Long solicitudId, String nuevoEstado) {
        Solicitud solicitud = obtenerPorId(solicitudId);
        solicitud.setEstado(nuevoEstado);
        solicitud.setFechaActualizacion(LocalDateTime.now());

        return repository.guardar(solicitud);
    }
}