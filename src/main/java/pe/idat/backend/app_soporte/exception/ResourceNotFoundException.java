package pe.idat.backend.app_soporte.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String recurso;
    private final Long id;

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
        this.recurso = null;
        this.id = null;
    }

    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " con ID " + id + " no encontrado");
        this.recurso = recurso;
        this.id = id;
    }
}
