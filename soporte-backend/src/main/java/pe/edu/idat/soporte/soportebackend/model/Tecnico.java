package pe.edu.idat.soporte.soportebackend.model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
public class Tecnico extends Persona{
    private String especialidad;
}
