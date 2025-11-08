package idat.eva1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class solicitudDTO {

    @Valid
    @NotNull(message = "El ID del cliente es obligatorio")
    private Integer idCliente;

    @Valid
    @NotNull(message = "El ID del tecnico es obligatorio")
    private Integer idTecnico;

    @Valid
    @NotBlank(message = "Por favor ingrese descripcion")
    private String descripcion;

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public Integer getIdTecnico() { return idTecnico; }
    public void setIdTecnico(Integer idTecnico) { this.idTecnico = idTecnico; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
