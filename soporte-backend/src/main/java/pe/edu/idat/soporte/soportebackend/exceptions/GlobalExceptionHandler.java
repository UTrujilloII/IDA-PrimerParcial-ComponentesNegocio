package pe.edu.idat.soporte.soportebackend.exceptions;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest req){
        List<Map<String,String>> errores=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error-> Map.of(
                        "campo",error.getField(),
                        "mensaje",error.getDefaultMessage() !=null?error.getDefaultMessage():"mensaje no disponible"
                ))
                .toList();

        ErrorResponse showError =ErrorResponse.builder()
                .fechaError(LocalDate.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("campos vacios")
                .mensaje("se encontraron errores de validacion")
                .errores(errores)
                .path(req.getDescription(false).replace("uri=",""))
                .build();
        return ResponseEntity.badRequest().body(showError);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handlerRecursoNoEncontradoException(RecursoNoEncontradoException ex , WebRequest req){
        ErrorResponse error = ErrorResponse.builder()
                .fechaError(LocalDate.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("recurso no encontrado")
                .mensaje(ex.getMessage())
                .path(req.getDescription(false).replace("uri=",""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception ex , WebRequest req){
        ErrorResponse error =ErrorResponse.builder()
                .fechaError(LocalDate.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error interno del servidor")
                .mensaje(ex.getMessage())
                .path(req.getDescription(false).replace("uri=",""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
