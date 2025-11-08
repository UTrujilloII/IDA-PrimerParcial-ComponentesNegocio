package idat.eva1.model;

import idat.eva1.dto.solicitudDTO;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud extends solicitudDTO {

    private Integer idSolicitud;
    private Integer idCliente;
    private Integer idTecnico;
    private String descripcion;
    private Date fechaRegistro;

}
