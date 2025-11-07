package pe.edu.idat.soporte.soportebackend.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    private Integer idSolicitud;

    private String titulo;

    private String descripcion;

    private Cliente cliente;

    private Tecnico tecnico;

    private EstadoSolicitud estado;

    private LocalDate fechaCreacion;
}
