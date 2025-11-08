package idat.eva1.model;

import idat.eva1.dto.clienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends clienteDTO {

    private Integer idCliente;
    private Integer dniCliente;
    private String apellidoPaternoCliente;
    private String apellidoMaternoCliente;
    private String nombreCliente;
    private Date fechaRegistro;
}
