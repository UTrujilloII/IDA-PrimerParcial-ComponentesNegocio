package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de validaciones (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> manejarValidacion(MethodArgumentNotValidException ex, WebRequest request) {
        String mensajeDetalle = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorException error = new ErrorException(
                mensajeDetalle,
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Operación Fallida"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ResourceNotFoundException -> 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorException> manejarNoEncontrado(ResourceNotFoundException ex, WebRequest request) {
        ErrorException error = new ErrorException(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "Recurso No Encontrado"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // BadRequestException -> 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorException> manejarBadRequest(BadRequestException ex, WebRequest request) {
        ErrorException error = new ErrorException(
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Solicitud Incorrecta"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Errores generales -> 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> manejarGenerales(Exception ex, WebRequest request) {
        ErrorException error = new ErrorException(
                ex.getMessage() != null ? ex.getMessage() : "Error interno",
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                "Error Interno"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}