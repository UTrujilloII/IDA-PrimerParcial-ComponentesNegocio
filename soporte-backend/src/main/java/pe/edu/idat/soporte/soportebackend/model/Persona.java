package pe.edu.idat.soporte.soportebackend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {



    private Integer id;


    private String dni;


    private String nombre;


    private String apellido;


    private String email;


    private String telefono;
}
