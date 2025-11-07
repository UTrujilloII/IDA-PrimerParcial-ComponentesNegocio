package com.tienda.appclient.service.impl;

import com.tienda.appclient.dto.TecnicoDTO;
import com.tienda.appclient.exception.DuplicateResourceException;
import com.tienda.appclient.exception.ResourceNotFoundException;
import com.tienda.appclient.mapper.TecnicoMapper;
import com.tienda.appclient.model.Tecnico;
import com.tienda.appclient.repository.TecnicoRepository;
import com.tienda.appclient.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TecnicoDTO> findAll() {
        return tecnicoRepository.findAll().stream()
                .map(TecnicoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TecnicoDTO findById(Integer id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + id));
        return TecnicoMapper.toDto(tecnico);
    }

    @Override
    @Transactional(readOnly = true)
    public TecnicoDTO findByCodigo(String codigo) {
        Tecnico tecnico = tecnicoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con código: " + codigo));
        return TecnicoMapper.toDto(tecnico);
    }

    @Override
    public TecnicoDTO create(TecnicoDTO tecnicoDTO) {
        // Normalizar código y email
        if (tecnicoDTO.getCodigo() != null) {
            tecnicoDTO.setCodigo(tecnicoDTO.getCodigo().trim());
        }
        if (tecnicoDTO.getEmail() != null) {
            tecnicoDTO.setEmail(tecnicoDTO.getEmail().trim().toLowerCase());
        }

        if (tecnicoRepository.existsByCodigo(tecnicoDTO.getCodigo())) {
            throw new DuplicateResourceException("Ya existe un técnico con el código: " + tecnicoDTO.getCodigo());
        }
        if (tecnicoRepository.existsByEmail(tecnicoDTO.getEmail())) {
            throw new DuplicateResourceException("Ya existe un técnico con el email: " + tecnicoDTO.getEmail());
        }

        Tecnico tecnico = TecnicoMapper.toEntity(tecnicoDTO);
        Tecnico saved = tecnicoRepository.save(tecnico);
        return TecnicoMapper.toDto(saved);
    }

    @Override
    public TecnicoDTO update(Integer id, TecnicoDTO tecnicoDTO) {
        Tecnico existing = tecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + id));

        // Normalizar entrada
        if (tecnicoDTO.getCodigo() != null) {
            tecnicoDTO.setCodigo(tecnicoDTO.getCodigo().trim());
        }
        if (tecnicoDTO.getEmail() != null) {
            tecnicoDTO.setEmail(tecnicoDTO.getEmail().trim().toLowerCase());
        }

        // Normalizar valores existentes para comparación
        String existingCodigoNormalizado = existing.getCodigo() != null ? existing.getCodigo().trim() : "";
        String existingEmailNormalizado = existing.getEmail() != null ? existing.getEmail().trim().toLowerCase() : "";

        // Validar código único si cambió: buscar por código y asegurarse que, si existe, no sea otro registro
        if (!existingCodigoNormalizado.equals(tecnicoDTO.getCodigo())) {
            tecnicoRepository.findByCodigo(tecnicoDTO.getCodigo()).ifPresent(t -> {
                if (!t.getId().equals(id)) {
                    throw new DuplicateResourceException("Ya existe un técnico con el código: " + tecnicoDTO.getCodigo());
                }
            });
        }

        // Validar email único si cambió: buscar por email y asegurarse que, si existe, no sea otro registro
        if (!existingEmailNormalizado.equals(tecnicoDTO.getEmail())) {
            tecnicoRepository.findByEmail(tecnicoDTO.getEmail()).ifPresent(t -> {
                if (!t.getId().equals(id)) {
                    throw new DuplicateResourceException("Ya existe un técnico con el email: " + tecnicoDTO.getEmail());
                }
            });
        }

        existing.setCodigo(tecnicoDTO.getCodigo());
        existing.setNombres(tecnicoDTO.getNombres());
        existing.setApellidos(tecnicoDTO.getApellidos());
        existing.setEmail(tecnicoDTO.getEmail());
        existing.setEspecialidad(tecnicoDTO.getEspecialidad());
        existing.setActivo(tecnicoDTO.getActivo());

        Tecnico updated = tecnicoRepository.save(existing);
        return TecnicoMapper.toDto(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Técnico no encontrado con ID: " + id);
        }
        tecnicoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TecnicoDTO> findByActivo(Boolean activo) {
        return tecnicoRepository.findByActivo(activo).stream()
                .map(TecnicoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TecnicoDTO> findByEspecialidad(String especialidad) {
        return tecnicoRepository.findByEspecialidad(especialidad).stream()
                .map(TecnicoMapper::toDto)
                .collect(Collectors.toList());
    }
}
