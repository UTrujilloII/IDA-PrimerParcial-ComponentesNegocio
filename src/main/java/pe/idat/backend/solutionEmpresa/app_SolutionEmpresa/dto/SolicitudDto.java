package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SolicitudDto {
    private Integer id;
    private @NotBlank(
            message = "La descripción es obligatoria",
            groups = {Crear.class, Actualizar.class}
    ) String descripcion;
    @Schema(
            description = "Fecha y hora de registro de la solicitud"
    )
    private LocalDateTime fechaRegistro;
    private @NotBlank(
            message = "El estado es obligatorio",
            groups = {Crear.class, Actualizar.class}
    ) String estado;
    private @NotNull(
            message = "El clienteId es obligatorio",
            groups = {Crear.class}
    ) Integer clienteId;
    private @NotNull(
            message = "El tecnicoId es obligatorio",
            groups = {Crear.class, Actualizar.class}
    ) Integer tecnicoId;

    public SolicitudDto() {
    }

    public SolicitudDto(Integer id, String descripcion, LocalDateTime fechaRegistro, String estado, Integer clienteId, Integer tecnicoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.clienteId = clienteId;
        this.tecnicoId = tecnicoId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTecnicoId() {
        return this.tecnicoId;
    }

    public void setTecnicoId(Integer tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public interface Actualizar {
    }

    public interface Crear {
    }
}
