package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.BadRequestException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.ResourceNotFoundException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Solicitud;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository.ClienteRepository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository.SolicitudRepository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository.TecnicoRepository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository, TecnicoRepository tecnicoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    public Solicitud guardarSolicitud(Solicitud solicitud) {
        if (solicitud.getDescripcion() != null && !solicitud.getDescripcion().trim().isEmpty()) {
            if (solicitud.getClienteId() != null && this.clienteRepository.existePorId(solicitud.getClienteId())) {
                if (solicitud.getTecnicoId() != null && !this.tecnicoRepository.existePorId(solicitud.getTecnicoId())) {
                    throw new BadRequestException("Técnico inexistente.");
                } else {
                    if (solicitud.getFechaRegistro() == null) {
                        solicitud.setFechaRegistro(LocalDateTime.now());
                    }

                    if (solicitud.getEstado() == null || solicitud.getEstado().trim().isEmpty()) {
                        solicitud.setEstado("PENDIENTE");
                    }

                    return this.solicitudRepository.guardar(solicitud);
                }
            } else {
                throw new BadRequestException("Cliente inexistente. Registre el cliente antes o envíe un clientId válido.");
            }
        } else {
            throw new BadRequestException("La descripción es obligatoria");
        }
    }

    public List<Solicitud> listarTodasLasSolicitudes() {
        return this.solicitudRepository.listarTodas();
    }

    public Optional<Solicitud> buscarSolicitudPorId(Integer id) {
        return this.solicitudRepository.buscarPorId(id);
    }

    public Solicitud actualizarSolicitud(Integer id, Solicitud solicitudActualizada) {
        Solicitud existente = (Solicitud)this.solicitudRepository.buscarPorId(id).orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + id));
        if (solicitudActualizada.getDescripcion() != null && !solicitudActualizada.getDescripcion().isBlank()) {
            existente.setDescripcion(solicitudActualizada.getDescripcion());
        }

        if (solicitudActualizada.getEstado() != null && !solicitudActualizada.getEstado().isBlank()) {
            existente.setEstado(solicitudActualizada.getEstado());
        }

        if (solicitudActualizada.getTecnicoId() != null) {
            if (!this.tecnicoRepository.existePorId(solicitudActualizada.getTecnicoId())) {
                throw new BadRequestException("El técnico con ID " + solicitudActualizada.getTecnicoId() + " no existe.");
            }

            existente.setTecnicoId(solicitudActualizada.getTecnicoId());
        }

        return this.solicitudRepository.guardar(existente);
    }

    public void eliminarSolicitudPorId(Integer id) {
        if (!this.solicitudRepository.existePorId(id)) {
            throw new ResourceNotFoundException("Solicitud no encontrada con id " + id);
        } else {
            this.solicitudRepository.eliminarPorId(id);
        }
    }

    public List<Solicitud> buscarSolicitudesPorClienteId(Integer clienteId) {
        return this.solicitudRepository.buscarPorClienteId(clienteId);
    }

    public List<Solicitud> buscarSolicitudesPorTecnicoId(Integer tecnicoId) {
        return this.solicitudRepository.buscarPorTecnicoId(tecnicoId);
    }

    public Solicitud asignarTecnicoAIdentificacion(Integer solicitudId, Integer tecnicoId) {
        Solicitud existente = (Solicitud)this.solicitudRepository.buscarPorId(solicitudId).orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id " + solicitudId));
        if (!this.tecnicoRepository.existePorId(tecnicoId)) {
            throw new BadRequestException("Técnico inexistente.");
        } else {
            existente.setTecnicoId(tecnicoId);
            existente.setEstado("EN_PROCESO");
            return this.solicitudRepository.guardar(existente);
        }
    }
}
