package idat.eva1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class tecnicoDTO {

    @Valid
    @NotNull(message = "Por favor ingresar número de DNI")
    private Integer dniTecnico;

    @Valid
    @NotNull(message = "Por favor ingresar apellido paterno")
    private String apellidoPaternoTecnico;

    @Valid
    @NotNull(message = "Por favor ingresar apellido materno")
    private String apellidoMaternoTecnico;

    @Valid
    @NotNull(message = "Por favor ingresar el (los) nombre(s)")
    private String nombreTecnico;

    @Valid
    @NotNull(message = "Por favor ingresar correo electronico")
    private String email;

    public Integer getDniTecnico() { return dniTecnico; }
    public void setDniTecnico(Integer dniTecnico) {this.dniTecnico = dniTecnico;}

    public String getApellidoPaternoTecnico() {return apellidoPaternoTecnico;}
    public void setApellidoPaternoTecnico(String apellidoPaternoTecnico){this.apellidoPaternoTecnico = apellidoPaternoTecnico;}

    public String getApellidoMaternoTecnico() {return apellidoMaternoTecnico;}
    public void setApellidoMaternoTecnico(String apellidoMaternoTecnico){this.apellidoMaternoTecnico = apellidoMaternoTecnico;}

    public String getNombreTecnico() {return nombreTecnico;}
    public void setNombreTecnico(String nombreTecnico){this.nombreTecnico = nombreTecnico;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}




}
