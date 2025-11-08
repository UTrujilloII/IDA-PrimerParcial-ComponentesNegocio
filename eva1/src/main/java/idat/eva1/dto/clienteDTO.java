package idat.eva1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import lombok.Data;

import java.awt.dnd.DnDConstants;


public class clienteDTO {

    @Valid
    @NotNull(message = "Por favor ingresar número de DNI")
    private Integer dniCliente;

    @Valid
    @NotNull(message = "Por favor ingresar apellido paterno")
    private String apellidoPaternoCliente;

    @Valid
    @NotNull(message = "Por favor ingresar apellido materno")
    private String apellidoMaternoCliente;

    @Valid
    @NotNull(message = "Por favor ingresar el (los) nombre(s)")
    private String nombreCliente;

    public Integer getDniCliente() { return dniCliente; }
    public void setDniCliente(Integer dniCliente) { this.dniCliente = dniCliente; }

    public String getApellidoPaternoCliente() { return apellidoPaternoCliente; }
    public void setApellidoPaternoCliente(String apellidoPaternoCliente) {this.apellidoPaternoCliente = apellidoPaternoCliente; }

    public String getApellidoMaternoCliente() { return apellidoMaternoCliente; }
    public void setApellidoMaternoCliente(String apellidoMaternoCliente) {this.apellidoMaternoCliente = apellidoMaternoCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) {this.nombreCliente = nombreCliente; }

}
