package pe.idat.backend.evaluacion.app_soporte.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Solicitud {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    private EstadoSolicitud estado;
    private LocalDateTime fechaCreacion;

    @NotNull(message = "El cliente no puede ser nulo")
    @Valid
    private Cliente cliente;
    private Tecnico tecnicoAsignado;

}