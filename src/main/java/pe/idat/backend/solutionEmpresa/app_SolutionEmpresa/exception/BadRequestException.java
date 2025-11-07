package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}