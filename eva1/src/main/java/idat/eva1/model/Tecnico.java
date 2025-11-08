package idat.eva1.model;


import idat.eva1.dto.tecnicoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tecnico extends tecnicoDTO {

    private Integer idTecnico;
    private Integer dniTecnico;
    private String apellidoPaternoTecnico;
    private String apellidoMaternoTecnico;
    private String nombreTecnico;
    private String email;

}
