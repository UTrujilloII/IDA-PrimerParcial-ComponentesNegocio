package com.tienda.appclient.mapper;

import com.tienda.appclient.dto.SolicitudDTO;
import com.tienda.appclient.model.Cliente;
import com.tienda.appclient.model.Solicitud;
import com.tienda.appclient.model.Tecnico;

public class SolicitudMapper {

    public static SolicitudDTO toDto(Solicitud solicitud) {
        if (solicitud == null) return null;

        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(solicitud.getId());
        dto.setCodigo(solicitud.getCodigo());
        dto.setAsunto(solicitud.getAsunto());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setPrioridad(solicitud.getPrioridad());
        dto.setFechaCreacion(solicitud.getFechaCreacion());
        dto.setFechaAsignacion(solicitud.getFechaAsignacion());
        dto.setFechaResolucion(solicitud.getFechaResolucion());
        dto.setObservaciones(solicitud.getObservaciones());

        if (solicitud.getCliente() != null) {
            dto.setClienteId(solicitud.getCliente().getId());
            dto.setCliente(ClienteMapper.toDto(solicitud.getCliente()));
        }

        if (solicitud.getTecnico() != null) {
            dto.setTecnicoId(solicitud.getTecnico().getId());
            dto.setTecnico(TecnicoMapper.toDto(solicitud.getTecnico()));
        }

        return dto;
    }

    public static Solicitud toEntity(SolicitudDTO dto, Cliente cliente, Tecnico tecnico) {
        if (dto == null) return null;

        Solicitud solicitud = new Solicitud();
        solicitud.setId(dto.getId());
        solicitud.setCodigo(dto.getCodigo());
        solicitud.setAsunto(dto.getAsunto());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setEstado(dto.getEstado());
        solicitud.setPrioridad(dto.getPrioridad());
        solicitud.setFechaCreacion(dto.getFechaCreacion());
        solicitud.setFechaAsignacion(dto.getFechaAsignacion());
        solicitud.setFechaResolucion(dto.getFechaResolucion());
        solicitud.setObservaciones(dto.getObservaciones());
        solicitud.setCliente(cliente);
        solicitud.setTecnico(tecnico);

        return solicitud;
    }
}

