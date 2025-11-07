package com.empresa.soporte.controller;

import com.empresa.soporte.dto.ClienteDTO;
import com.empresa.soporte.dto.SolicitudDTO;
import com.empresa.soporte.dto.TecnicoDTO;
import com.empresa.soporte.model.Cliente;
import com.empresa.soporte.model.Solicitud;
import com.empresa.soporte.model.Tecnico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final List<Solicitud> solicitudes = new ArrayList<>();

    // 🟦 GET general
    @Operation(summary = "Listar solicitudes",
            description = "Devuelve HTML (modo oscuro) si el User-Agent no luce como cliente API. "
                    + "Si es Postman/cURL/Swagger, devuelve JSON con la lista.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SolicitudDTO.class))),
                    @Content(mediaType = "text/html")
            })
    @GetMapping
    public ResponseEntity<?> listar(@RequestHeader(value = "User-Agent", required = false) String userAgent) {
        boolean clienteApi = esClienteApi(userAgent);

        if (clienteApi) {
            if (solicitudes.isEmpty()) {
                return ResponseEntity.ok(Map.of("mensaje", "No hay solicitudes registradas."));
            }
            List<SolicitudDTO> listaDTO = new ArrayList<>();
            for (Solicitud s : solicitudes) listaDTO.add(convertirEntidadADTO(s));
            return ResponseEntity.ok(listaDTO);
        }

        // HTML
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder html = new StringBuilder("""
            <!DOCTYPE html><html lang="es"><head>
            <meta charset="UTF-8"><title>Solicitudes</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <style>body{background:#121212;color:white;font-family:'Poppins',sans-serif;}
            h1{text-align:center;color:#0d6efd;margin:30px 0;}
            table{animation:fadeIn 0.8s ease-in-out;}
            @keyframes fadeIn{from{opacity:0;transform:translateY(20px);}to{opacity:1;transform:translateY(0);}}
            .estado-Pendiente{color:#ffc107;font-weight:bold;}
            .estado-En\\ proceso{color:#0dcaf0;font-weight:bold;}
            .estado-Finalizado{color:#198754;font-weight:bold;}
            .estado-Cancelado{color:#dc3545;font-weight:bold;}
            </style></head><body><div class='container mt-4'>
            <h1>📋 Lista de Solicitudes</h1>
            <table class='table table-dark table-bordered table-hover align-middle text-center'>
            <thead class='table-primary'><tr>
            <th>ID</th><th>Título</th><th>Descripción</th><th>Cliente</th><th>Correo Cliente</th>
            <th>Técnico</th><th>Estado</th><th>Fecha</th><th>Hora</th></tr></thead><tbody>
        """);

        if (solicitudes.isEmpty()) {
            html.append("<tr><td colspan='9'>No hay solicitudes registradas.</td></tr>");
        } else {
            for (Solicitud s : solicitudes) {
                String fecha = s.getFechaRegistro() != null ? s.getFechaRegistro().format(formatoFecha) : "—";
                String hora = s.getFechaRegistro() != null ? s.getFechaRegistro().format(formatoHora) : "—";
                html.append(String.format("""
                    <tr><td>%d</td><td>%s</td><td>%s</td>
                    <td>%s</td><td>%s</td><td>%s</td>
                    <td class='estado-%s'>%s</td><td>%s</td><td>%s</td></tr>
                """,
                        s.getId(),
                        nulo(s.getTitulo()),
                        nulo(s.getDescripcion()),
                        s.getCliente() != null ? nulo(s.getCliente().getNombre()) : "—",
                        s.getCliente() != null ? nulo(s.getCliente().getCorreo()) : "—",
                        s.getTecnico() != null ? nulo(s.getTecnico().getNombre()) : "—",
                        s.getEstado() != null ? s.getEstado().replace(" ", "\\ ") : "Pendiente",
                        s.getEstado() != null ? s.getEstado() : "Pendiente",
                        fecha, hora
                ));
            }
        }

        html.append("</tbody></table></div></body></html>");
        return ResponseEntity.ok().header("Content-Type", "text/html; charset=UTF-8").body(html.toString());
    }

    // 🟦 GET por ID
    @Operation(summary = "Obtener solicitud por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = SolicitudDTO.class))),
            @ApiResponse(responseCode = "404", description = "No encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id,
                                     @RequestHeader(value = "User-Agent", required = false) String userAgent) {
        Solicitud encontrada = buscarPorIdEnMemoria(id);
        if (encontrada == null)
            return manejarError(userAgent, "Solicitud con ID " + id + " no encontrada", 404);
        return ResponseEntity.ok(convertirEntidadADTO(encontrada));
    }

    // 🟩 POST - Crear
    @Operation(summary = "Registrar una o varias solicitudes",
            description = "Envía una o más solicitudes con fecha y hora en formato YYYY-MM-DD y HH:mm. Si algún ítem tiene errores, se devolverá 400.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitudes registradas"),
            @ApiResponse(responseCode = "400", description = "Errores de validación")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> crear(@RequestBody @NotNull List<SolicitudDTO> lista) {
        List<Map<String, Object>> errores = new ArrayList<>();
        List<SolicitudDTO> registradas = new ArrayList<>();

        int index = 1;
        for (SolicitudDTO dto : lista) {
            List<String> faltas = validarCampos(dto, index);
            if (!faltas.isEmpty()) {
                errores.add(Map.of("ítem", index, "errores", faltas));
            } else {
                try {
                    Solicitud entidad = convertirDTOAEntidad(dto);
                    solicitudes.add(entidad);
                    registradas.add(convertirEntidadADTO(entidad));
                } catch (Exception e) {
                    errores.add(Map.of("ítem", index, "errores", List.of(e.getMessage())));
                }
            }
            index++;
        }

        if (!errores.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "❌ Algunos registros tienen errores de validación",
                    "detalles", errores
            ));
        }

        return ResponseEntity.ok(Map.of("mensaje", "✅ Solicitudes registradas correctamente", "data", registradas));
    }

    // 🟨 PUT - Actualizar
    @Operation(summary = "Actualizar una solicitud por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación"),
            @ApiResponse(responseCode = "404", description = "No encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody SolicitudDTO dto) {
        Solicitud existente = buscarPorIdEnMemoria(id);
        if (existente == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Solicitud no encontrada"));

        List<String> faltas = validarCampos(dto, 1);
        if (!faltas.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "❌ Error de validación", "errores", faltas));
        }

        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());

        if (dto.getCliente() != null) {
            Cliente c = new Cliente();
            c.setId(dto.getCliente().getId());
            c.setNombre(dto.getCliente().getNombre());
            c.setCorreo(dto.getCliente().getCorreo());
            existente.setCliente(c);
        }

        if (dto.getTecnico() != null) {
            Tecnico t = new Tecnico();
            t.setId(dto.getTecnico().getId());
            t.setNombre(dto.getTecnico().getNombre());
            t.setEspecialidad(dto.getTecnico().getEspecialidad());
            existente.setTecnico(t);
        }

        try {
            existente.setFechaRegistro(LocalDateTime.parse(dto.getFecha() + "T" + dto.getHora() + ":00"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "❌ Error en fecha/hora",
                    "detalle", "La fecha u hora no tienen el formato correcto. Use YYYY-MM-DD y HH:mm"
            ));
        }

        return ResponseEntity.ok(Map.of("mensaje", "🟡 Solicitud actualizada correctamente", "data", convertirEntidadADTO(existente)));
    }

    // 🟥 DELETE eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Solicitud encontrada = buscarPorIdEnMemoria(id);
        if (encontrada == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Solicitud no encontrada"));
        solicitudes.removeIf(s -> s.getId().equals(id));
        return ResponseEntity.ok(Map.of("mensaje", "🗑️ Solicitud eliminada correctamente"));
    }

    // ⚙️ VALIDACIONES
    private List<String> validarCampos(SolicitudDTO dto, int index) {
        List<String> errores = new ArrayList<>();

        if (vacio(dto.getTitulo()))
            errores.add("[TÍTULO]: #" + index + " → El campo 'titulo' es obligatorio");
        else if (!dto.getTitulo().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
            errores.add("[TÍTULO]: #" + index + " → Solo se permiten letras y espacios");

        if (vacio(dto.getDescripcion()))
            errores.add("[DESCRIPCIÓN]: El campo 'descripcion' es obligatorio");

        if (dto.getCliente() == null) {
            errores.add("[CLIENTE]: Debe indicar los datos del cliente");
        } else {
            ClienteDTO c = dto.getCliente();
            if (vacio(c.getNombre()))
                errores.add("[NOMBRE CLIENTE]: El nombre del cliente es obligatorio");
            else if (!c.getNombre().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
                errores.add("[NOMBRE CLIENTE]: Solo puede contener letras");
            if (vacio(c.getCorreo()))
                errores.add("[CORREO CLIENTE]: El correo del cliente es obligatorio");
            else if (!c.getCorreo().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                errores.add("[CORREO CLIENTE]: El correo no tiene un formato válido");
        }

        if (dto.getTecnico() == null) {
            errores.add("[TÉCNICO]: Debe indicar los datos del técnico");
        } else {
            TecnicoDTO t = dto.getTecnico();
            if (vacio(t.getNombre()))
                errores.add("[NOMBRE TÉCNICO]: El nombre del técnico es obligatorio");
            else if (!t.getNombre().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
                errores.add("[NOMBRE TÉCNICO]: Solo puede contener letras");
            if (vacio(t.getEspecialidad()))
                errores.add("[ESPECIALIDAD]: La especialidad del técnico es obligatoria");
            else if (!t.getEspecialidad().matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$"))
                errores.add("[ESPECIALIDAD]: Solo puede contener letras");
        }

        if (vacio(dto.getFecha()) || !dto.getFecha().matches("^\\d{4}-\\d{2}-\\d{2}$"))
            errores.add("[FECHA]: La fecha debe tener el formato YYYY-MM-DD");

        if (vacio(dto.getHora()) || !dto.getHora().matches("^(?:[01]\\d|2[0-3]):[0-5]\\d$"))
            errores.add("[HORA]: La hora debe tener el formato HH:mm (00–23:59)");

        return errores;
    }

    // 🧩 Conversión DTO ↔ Entidad
    private Solicitud convertirDTOAEntidad(SolicitudDTO dto) {
        Solicitud s = new Solicitud();
        s.setId((long) (solicitudes.size() + 1));
        s.setTitulo(dto.getTitulo());
        s.setDescripcion(dto.getDescripcion());
        s.setEstado(dto.getEstado() != null ? dto.getEstado() : "Pendiente");

        if (dto.getCliente() != null) {
            Cliente c = new Cliente();
            c.setId(dto.getCliente().getId());
            c.setNombre(dto.getCliente().getNombre());
            c.setCorreo(dto.getCliente().getCorreo());
            s.setCliente(c);
        }

        if (dto.getTecnico() != null) {
            Tecnico t = new Tecnico();
            t.setId(dto.getTecnico().getId());
            t.setNombre(dto.getTecnico().getNombre());
            t.setEspecialidad(dto.getTecnico().getEspecialidad());
            s.setTecnico(t);
        }

        // ✅ Fecha/hora manuales
        if (dto.getFecha() != null && dto.getHora() != null) {
            try {
                s.setFechaRegistro(LocalDateTime.parse(dto.getFecha() + "T" + dto.getHora() + ":00"));
            } catch (Exception e) {
                throw new IllegalArgumentException("[FECHA/HORA]: Formato inválido. Use YYYY-MM-DD y HH:mm");
            }
        } else {
            throw new IllegalArgumentException("[FECHA/HORA]: Ambos campos son obligatorios.");
        }

        return s;
    }

    private SolicitudDTO convertirEntidadADTO(Solicitud s) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(s.getId());
        dto.setTitulo(s.getTitulo());
        dto.setDescripcion(s.getDescripcion());
        dto.setEstado(s.getEstado());


        // ✅ derivar fecha y hora
        if (s.getFechaRegistro() != null) {
            dto.setFecha(s.getFechaRegistro().toLocalDate().toString());
            dto.setHora(s.getFechaRegistro().toLocalTime().withSecond(0).toString());
        }

        if (s.getCliente() != null)
            dto.setCliente(new ClienteDTO(s.getCliente().getId(), s.getCliente().getNombre(), s.getCliente().getCorreo()));
        if (s.getTecnico() != null)
            dto.setTecnico(new TecnicoDTO(s.getTecnico().getId(), s.getTecnico().getNombre(), s.getTecnico().getEspecialidad()));

        return dto;
    }

    //Manejadores de errores
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("error", "❌ Validación fallida");
        Map<String, String> detalles = new LinkedHashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors())
            detalles.put(err.getField(), err.getDefaultMessage());
        respuesta.put("detalles", detalles);
        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> manejarArgumentosInvalidos(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", "❌ Error de validación de campos",
                "detalle", ex.getMessage()
        ));
    }

    // Auxiliares
    private boolean esClienteApi(String userAgent) {
        if (userAgent == null) return false;
        String lower = userAgent.toLowerCase();
        return lower.contains("postman") || lower.contains("curl") || lower.contains("insomnia") || lower.contains("swagger");
    }

    private String nulo(String texto) { return texto != null ? texto : "—"; }

    private boolean vacio(String s) { return s == null || s.trim().isEmpty(); }

    private Solicitud buscarPorIdEnMemoria(Long id) {
        return solicitudes.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    private ResponseEntity<?> manejarError(String userAgent, String mensaje, int status) {
        return ResponseEntity.status(status).body(Map.of("error", mensaje));
    }
}
