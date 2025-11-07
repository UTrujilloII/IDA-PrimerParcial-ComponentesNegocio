package pe.idat.backend.evaluacion.app_soporte.service;

import org.springframework.stereotype.Service;
import pe.idat.backend.evaluacion.app_soporte.dto.ClienteDto;
import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudRequestDto;
import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudResponseDto;
import pe.idat.backend.evaluacion.app_soporte.dto.TecnicoDto;
import pe.idat.backend.evaluacion.app_soporte.model.Cliente;
import pe.idat.backend.evaluacion.app_soporte.model.EstadoSolicitud;
import pe.idat.backend.evaluacion.app_soporte.model.Solicitud;
import pe.idat.backend.evaluacion.app_soporte.model.Tecnico;
import pe.idat.backend.evaluacion.app_soporte.repository.SolicitudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public SolicitudResponseDto save(SolicitudRequestDto requestDto) {
        Solicitud solicitud = toModel(requestDto);
        solicitud.setFechaCreacion(LocalDateTime.now());
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        Solicitud solicitudGuardada = solicitudRepository.save(solicitud);
        return toResponseDto(solicitudGuardada);
    }

    @Override
    public List<SolicitudResponseDto> findAll() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();

        return solicitudes.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SolicitudResponseDto> findById(Long id) {

        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(id);

        return solicitudOpt.map(this::toResponseDto);
    }

    @Override
    public Optional<SolicitudResponseDto> update(Long id, SolicitudRequestDto requestDto) {

        Optional<Solicitud> solicitudExistenteOpt = solicitudRepository.findById(id);

        if (solicitudExistenteOpt.isEmpty()) {
            return Optional.empty();
        }

        Solicitud solicitudExistente = solicitudExistenteOpt.get();
        Solicitud solicitudActualizada = toModel(requestDto);
        solicitudActualizada.setId(id);
        solicitudActualizada.setFechaCreacion(solicitudExistente.getFechaCreacion()); // Mantenemos fecha original

        Optional<Solicitud> actualizadaOpt = solicitudRepository.update(id, solicitudActualizada);

        return actualizadaOpt.map(this::toResponseDto);
    }


    @Override
    public boolean deleteById(Long id) {

        return solicitudRepository.deleteById(id);
    }

    private SolicitudResponseDto toResponseDto(Solicitud solicitud) {
        SolicitudResponseDto dto = new SolicitudResponseDto();
        dto.setId(solicitud.getId());
        dto.setTitulo(solicitud.getTitulo());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaCreacion(solicitud.getFechaCreacion());

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre(solicitud.getCliente().getNombre());
        clienteDto.setEmail(solicitud.getCliente().getEmail());
        dto.setCliente(clienteDto);

        if (solicitud.getTecnicoAsignado() != null) {
            TecnicoDto tecnicoDto = new TecnicoDto();
            tecnicoDto.setNombre(solicitud.getTecnicoAsignado().getNombre());
            tecnicoDto.setEspecialidad(solicitud.getTecnicoAsignado().getEspecialidad());
            dto.setTecnicoAsignado(tecnicoDto);
        }

        return dto;
    }

    private Solicitud toModel(SolicitudRequestDto dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setTitulo(dto.getTitulo());
        solicitud.setDescripcion(dto.getDescripcion());

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getCliente().getNombre());
        cliente.setEmail(dto.getCliente().getEmail());
        solicitud.setCliente(cliente);

        if (dto.getTecnicoAsignado() != null) {
            Tecnico tecnico = new Tecnico();
            tecnico.setNombre(dto.getTecnicoAsignado().getNombre());
            tecnico.setEspecialidad(dto.getTecnicoAsignado().getEspecialidad());
            solicitud.setTecnicoAsignado(tecnico);
        }

        return solicitud;
    }
}