package com.tienda.appclient.service.impl;

import com.tienda.appclient.dto.SolicitudDTO;
import com.tienda.appclient.exception.BusinessRuleException;
import com.tienda.appclient.exception.DuplicateResourceException;
import com.tienda.appclient.exception.ResourceNotFoundException;
import com.tienda.appclient.mapper.SolicitudMapper;
import com.tienda.appclient.model.Cliente;
import com.tienda.appclient.model.Solicitud;
import com.tienda.appclient.model.Tecnico;
import com.tienda.appclient.repository.ClienteRepository;
import com.tienda.appclient.repository.SolicitudRepository;
import com.tienda.appclient.repository.TecnicoRepository;
import com.tienda.appclient.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findAll() {
        return solicitudRepository.findAll().stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudDTO findById(Integer id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + id));
        return SolicitudMapper.toDto(solicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudDTO findByCodigo(String codigo) {
        Solicitud solicitud = solicitudRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con código: " + codigo));
        return SolicitudMapper.toDto(solicitud);
    }

    @Override
    public SolicitudDTO create(SolicitudDTO solicitudDTO) {
        if (solicitudRepository.existsByCodigo(solicitudDTO.getCodigo())) {
            throw new DuplicateResourceException("Ya existe una solicitud con el código: " + solicitudDTO.getCodigo());
        }

        Cliente cliente = clienteRepository.findById(solicitudDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + solicitudDTO.getClienteId()));

        Tecnico tecnico = null;
        if (solicitudDTO.getTecnicoId() != null) {
            tecnico = tecnicoRepository.findById(solicitudDTO.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + solicitudDTO.getTecnicoId()));
        }

        Solicitud solicitud = SolicitudMapper.toEntity(solicitudDTO, cliente, tecnico);

        // Establecer valores por defecto si no vienen
        if (solicitud.getEstado() == null) {
            solicitud.setEstado(Solicitud.EstadoSolicitud.PENDIENTE);
        }
        if (solicitud.getPrioridad() == null) {
            solicitud.setPrioridad(Solicitud.PrioridadSolicitud.MEDIA);
        }
        if (solicitud.getFechaCreacion() == null) {
            solicitud.setFechaCreacion(LocalDateTime.now());
        }

        Solicitud saved = solicitudRepository.save(solicitud);
        return SolicitudMapper.toDto(saved);
    }

    @Override
    public SolicitudDTO update(Integer id, SolicitudDTO solicitudDTO) {
        Solicitud existing = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + id));

        // Validar código único si cambió
        if (!existing.getCodigo().equals(solicitudDTO.getCodigo()) &&
            solicitudRepository.existsByCodigo(solicitudDTO.getCodigo())) {
            throw new DuplicateResourceException("Ya existe una solicitud con el código: " + solicitudDTO.getCodigo());
        }

        Cliente cliente = clienteRepository.findById(solicitudDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + solicitudDTO.getClienteId()));

        Tecnico tecnico = null;
        if (solicitudDTO.getTecnicoId() != null) {
            tecnico = tecnicoRepository.findById(solicitudDTO.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + solicitudDTO.getTecnicoId()));
        }

        existing.setCodigo(solicitudDTO.getCodigo());
        existing.setAsunto(solicitudDTO.getAsunto());
        existing.setDescripcion(solicitudDTO.getDescripcion());
        existing.setCliente(cliente);
        existing.setTecnico(tecnico);
        existing.setEstado(solicitudDTO.getEstado());
        existing.setPrioridad(solicitudDTO.getPrioridad());
        existing.setObservaciones(solicitudDTO.getObservaciones());

        Solicitud updated = solicitudRepository.save(existing);
        return SolicitudMapper.toDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!solicitudRepository.existsById(id)) {
            throw new ResourceNotFoundException("Solicitud no encontrada con ID: " + id);
        }
        solicitudRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByEstado(Solicitud.EstadoSolicitud estado) {
        return solicitudRepository.findByEstado(estado).stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByPrioridad(Solicitud.PrioridadSolicitud prioridad) {
        return solicitudRepository.findByPrioridad(prioridad).stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByCliente(Integer clienteId) {
        // Validar que el cliente exista
        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId));

        return solicitudRepository.findByClienteId(clienteId).stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByTecnico(Integer tecnicoId) {
        // Validar que el técnico exista
        tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + tecnicoId));

        return solicitudRepository.findByTecnicoId(tecnicoId).stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudDTO> findSinAsignar() {
        return solicitudRepository.findByTecnicoIsNull().stream()
                .map(SolicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudDTO asignarTecnico(Integer solicitudId, Integer tecnicoId) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + solicitudId));

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + tecnicoId));

        if (!tecnico.getActivo()) {
            throw new BusinessRuleException("No se puede asignar: el técnico no está activo");
        }

        solicitud.setTecnico(tecnico);
        solicitud.setFechaAsignacion(LocalDateTime.now());

        // Cambiar estado a ASIGNADA si estaba PENDIENTE
        if (solicitud.getEstado() == Solicitud.EstadoSolicitud.PENDIENTE) {
            solicitud.setEstado(Solicitud.EstadoSolicitud.ASIGNADA);
        }

        Solicitud updated = solicitudRepository.save(solicitud);
        return SolicitudMapper.toDto(updated);
    }

    @Override
    public SolicitudDTO cambiarEstado(Integer solicitudId, Solicitud.EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con ID: " + solicitudId));

        solicitud.setEstado(nuevoEstado);

        // Si se marca como resuelta, establecer fecha de resolución
        if (nuevoEstado == Solicitud.EstadoSolicitud.RESUELTA && solicitud.getFechaResolucion() == null) {
            solicitud.setFechaResolucion(LocalDateTime.now());
        }

        Solicitud updated = solicitudRepository.save(solicitud);
        return SolicitudMapper.toDto(updated);
    }
}

