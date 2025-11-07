package pe.edu.idat.soporte.soportebackend.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private LocalDate fechaError;
    private int status;
    private String error;
    private String mensaje;
    private String path;
    private List<Map<String,String>> errores;
}
