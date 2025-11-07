package pe.idat.backend.app_soporte.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    private Tecnico tecnicoAsignado;

    @NotBlank(message = "La prioridad es obligatoria")
    private String prioridad; // BAJA, MEDIA, ALTA

    @NotBlank(message = "El estado es obligatorio")
    private String estado; // PENDIENTE, EN_PROCESO, RESUELTO, CERRADO

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructor personalizado para crear solicitudes nuevas
    public Solicitud(Long id, String titulo, String descripcion, Cliente cliente,
                     String prioridad, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Sobrescribir setEstado para actualizar fechaActualizacion
    public void setEstado(String estado) {
        this.estado = estado;
        this.fechaActualizacion = LocalDateTime.now();
    }
}