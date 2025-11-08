package idat.eva1.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //no identificadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> excepcionGeneral(Exception ex) {
        return new ResponseEntity<>("Error no identificado: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
