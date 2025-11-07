package com.tienda.appclient.mapper;

import com.tienda.appclient.dto.ClienteDTO;
import com.tienda.appclient.model.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDto(Cliente cliente) {
        if (cliente == null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setDni(cliente.getDni());
        dto.setApellidoPaterno(cliente.getApellidoPaterno());
        dto.setApellidoMaterno(cliente.getApellidoMaterno());
        dto.setNombres(cliente.getNombres());
        dto.setFechaNacimiento(cliente.getFechaNacimiento());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setDni(dto.getDni());
        c.setApellidoPaterno(dto.getApellidoPaterno());
        c.setApellidoMaterno(dto.getApellidoMaterno());
        c.setNombres(dto.getNombres());
        c.setFechaNacimiento(dto.getFechaNacimiento());
        return c;
    }
}

