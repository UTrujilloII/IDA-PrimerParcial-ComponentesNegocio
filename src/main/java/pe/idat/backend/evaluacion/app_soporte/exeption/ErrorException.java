package pe.idat.backend.evaluacion.app_soporte.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorException
{
    private LocalDateTime fechaHora;
    private int status;
    private String error;
    private String mensaje;
    private String ruta;

}