package pe.idat.backend.evaluacion.app_soporte.dto;

import lombok.Data;
import pe.idat.backend.evaluacion.app_soporte.model.EstadoSolicitud;

import java.time.LocalDateTime;

@Data
public class SolicitudResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private EstadoSolicitud estado;
    private LocalDateTime fechaCreacion;
    private ClienteDto cliente;
    private TecnicoDto tecnicoAsignado;
}