package pe.idat.backend.evaluacion.app_soporte.exeption; // (Asegúrate que 'exeption' esté bien escrito, parece que falta una 'c')

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        String error = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(m -> m.getField() + ": " + m.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorException obj = ErrorException.builder()
                .fechaHora(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value()) // 400
                .error("Error de Validación")
                .mensaje(error)
                .ruta(request.getDescription(false))
                .build();

        return ResponseEntity.badRequest().body(obj);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorException> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        ErrorException obj = ErrorException.builder()
                .fechaHora(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value()) // 404
                .error("Recurso No Encontrado")
                .mensaje(ex.getMessage())
                .ruta(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorException> handleGenericException(Exception ex, WebRequest request) {

        ErrorException obj = ErrorException.builder()
                .fechaHora(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Error Interno del Servidor")
                .mensaje("Ocurrió un error inesperado. Contacte al administrador.")
                .ruta(request.getDescription(false).replace("uri=", ""))
                .build();

        System.err.println("Error no capturado: " + ex.getMessage());
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(obj);
    }
}