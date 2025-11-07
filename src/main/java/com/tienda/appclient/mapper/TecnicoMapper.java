package com.tienda.appclient.mapper;

import com.tienda.appclient.dto.TecnicoDTO;
import com.tienda.appclient.model.Tecnico;

public class TecnicoMapper {

    public static TecnicoDTO toDto(Tecnico tecnico) {
        if (tecnico == null) return null;

        TecnicoDTO dto = new TecnicoDTO();
        dto.setId(tecnico.getId());
        dto.setCodigo(tecnico.getCodigo());
        dto.setNombres(tecnico.getNombres());
        dto.setApellidos(tecnico.getApellidos());
        dto.setEmail(tecnico.getEmail());
        dto.setEspecialidad(tecnico.getEspecialidad());
        dto.setActivo(tecnico.getActivo());
        return dto;
    }

    public static Tecnico toEntity(TecnicoDTO dto) {
        if (dto == null) return null;

        Tecnico tecnico = new Tecnico();
        tecnico.setId(dto.getId());
        tecnico.setCodigo(dto.getCodigo());
        tecnico.setNombres(dto.getNombres());
        tecnico.setApellidos(dto.getApellidos());
        tecnico.setEmail(dto.getEmail());
        tecnico.setEspecialidad(dto.getEspecialidad());
        tecnico.setActivo(dto.getActivo());
        return tecnico;
    }
}

