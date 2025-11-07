package pe.edu.idat.soporte.soportebackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SolicitudDTO {
    @NotBlank(message = "el titulo es obligatorio")
    @Size(min = 3 ,max = 40, message = "El nombre debe tener entre 3 y 40 caracteres")
    private String titulo;

    @NotBlank(message = "La descripcion obligatoria")
    @Size(min = 5 ,max = 250, message = "La descripcion debe tener entre 5 y 250 caracteres")
    private String descripcion;



    @NotNull(message = "no puede estar vacio campo cliente")
    private Integer idCliente;


    @NotNull(message = "no puede estar vacio campo tecnico")
    private Integer idTecnico;
}
