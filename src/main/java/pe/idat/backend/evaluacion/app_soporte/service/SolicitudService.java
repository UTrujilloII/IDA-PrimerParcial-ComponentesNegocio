package pe.idat.backend.evaluacion.app_soporte.service;

import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudRequestDto;
import pe.idat.backend.evaluacion.app_soporte.dto.SolicitudResponseDto;

import java.util.List;
import java.util.Optional;

public interface SolicitudService {

    List<SolicitudResponseDto> findAll();
    Optional<SolicitudResponseDto> findById(Long id);
    SolicitudResponseDto save(SolicitudRequestDto requestDto);
    Optional<SolicitudResponseDto> update(Long id, SolicitudRequestDto requestDto);

    boolean deleteById(Long id);
}