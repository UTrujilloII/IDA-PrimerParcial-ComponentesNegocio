# 🧩 Sistema de Soporte Técnico – CRUD de Solicitudes

## 📖 Descripción general
Este proyecto es una **API REST** desarrollada en **Spring Boot** que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre la entidad `Solicitud`.

Los datos se almacenan **en memoria** mediante un `HashMap`, por lo que no se necesita una base de datos.  
Ideal para pruebas, aprendizaje o demostraciones académicas.

---

## 🏗️ Arquitectura del proyecto

| Capa | Descripción | Paquete |
|------|--------------|----------|
| **Controller** | Expone los endpoints HTTP (API REST). | `com.empresa.soporte_tecnico.controller` |
| **Service** | Contiene la lógica del negocio y gestiona los datos en memoria. | `com.empresa.soporte_tecnico.service` |
| **Model** | Define la estructura de la entidad `Solicitud`. | `com.empresa.soporte_tecnico.model` |
| **Config** | Contiene configuraciones como Swagger. | `com.empresa.soporte_tecnico.config` |

---

## ⚙️ Requisitos técnicos

- **Java:** 21 o superior  
- **Spring Boot:** 3.x  
- **Dependencias necesarias:**
  - `spring-boot-starter-web`
  - `spring-boot-starter-validation`
  - `springdoc-openapi-starter-webmvc-ui` (para Swagger UI)

---

▶️ Comandos de ejecución

Clonar el proyecto desde GitHub

git clone https://github.com/tu-usuario/soporte-tecnico.git
cd soporte-tecnico


Compilar y construir

mvn clean install


Ejecutar la aplicación

mvn spring-boot:run


Abrir en el navegador

http://localhost:8080/solicitudes



## 🧾 Entidad: Solicitud

```java
package com.empresa.soporte_tecnico.model;

public class Solicitud {

    private Long id;
    private String descripcion;
    private String estado;
    private String prioridad;

    // Getters y Setters
}

🧠 Lógica de negocio
Interface SolicitudService

package com.empresa.soporte_tecnico.service;

import com.empresa.soporte_tecnico.model.Solicitud;
import java.util.List;

public interface SolicitudService {
    Solicitud crearSolicitud(Solicitud solicitud);
    List<Solicitud> obtenerSolicitudes();
    Solicitud obtenerPorId(Long id);
    Solicitud actualizarSolicitud(Long id, Solicitud solicitud);
    void eliminarSolicitud(Long id);
}

Implementación SolicitudServiceImpl

package com.empresa.soporte_tecnico.service;

import com.empresa.soporte_tecnico.model.Solicitud;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final Map<Long, Solicitud> solicitudes = new HashMap<>();
    private Long contador = 1L;

    @Override
    public Solicitud crearSolicitud(Solicitud solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula");
        }
        solicitud.setId(contador++);
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    @Override
    public List<Solicitud> obtenerSolicitudes() {
        return new ArrayList<>(solicitudes.values());
    }

    @Override
    public Solicitud obtenerPorId(Long id) {
        return solicitudes.get(id);
    }

    @Override
    public Solicitud actualizarSolicitud(Long id, Solicitud solicitud) {
        if (!solicitudes.containsKey(id)) return null;
        solicitud.setId(id);
        solicitudes.put(id, solicitud);
        return solicitud;
    }

    @Override
    public void eliminarSolicitud(Long id) {
        solicitudes.remove(id);
    }
}

🌐 Controlador REST

package com.empresa.soporte_tecnico.controller;

import com.empresa.soporte_tecnico.model.Solicitud;
import com.empresa.soporte_tecnico.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping
    public Solicitud crearSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.crearSolicitud(solicitud);
    }

    @GetMapping
    public List<Solicitud> obtenerSolicitudes() {
        return solicitudService.obtenerSolicitudes();
    }

    @GetMapping("/{id}")
    public Solicitud obtenerSolicitudPorId(@PathVariable Long id) {
        return solicitudService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Solicitud actualizarSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        return solicitudService.actualizarSolicitud(id, solicitud);
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
    }
}


🚀 Endpoints disponibles
Método	Endpoint	Descripción	Ejemplo de uso
POST	/solicitudes	Crea una nueva solicitud	JSON con los datos
GET	/solicitudes	Lista todas las solicitudes	—
GET	/solicitudes/{id}	Obtiene una solicitud por su ID	/solicitudes/1
PUT	/solicitudes/{id}	Actualiza una solicitud existente	JSON actualizado
DELETE	/solicitudes/{id}	Elimina una solicitud	/solicitudes/1


📬 Ejemplo (Postman)
Crear Solicitud (POST)

URL:
http://localhost:8080/solicitudes

Body:

{
  "id": 1,
  "descripcion": "Laptop malograda",
  "cliente": {
    "id": 2,
    "nombre": "pedro pascal",
    "correo": "pedro@gmail.com"
  },
  "tecnicoAsignado": {
    "id": 3,
    "nombre": "pedro suarez",
    "especialidad": "Soporte TI"
  },
  "estado": "NUEVO, EN PROCESO, COMPLETADO "
}

🧩 Swagger Configuración

package com.empresa.soporte_tecnico.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI soporteTecnicoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Soporte Técnico")
                        .version("1.0")
                        .description("Documentación del CRUD de Solicitudes")
                        .contact(new Contact()
                                .name("Departamento de Sistemas")
                                .email("soporte@empresa.com")));
    }
}

Acceso Swagger UI:
👉 http://localhost:8080/swagger-ui/index.html

🧱 Estructura del proyecto

src/main/java/com/empresa/soporte_tecnico
│
├── config/
│   └── SwaggerConfig.java
│
├── controller/
│   └── SolicitudController.java
│
├── exception/
│   └── GlobalExceptionHandler.java
│
├── model/
│   └── Cliente.java 
│        Solicitud.java 
│        Tecnico.java 	
│
└── service/
    ├── SolicitudService.java
    └── SolicitudServiceImpl.java

🧪 Flujo de funcionamiento

El cliente envía la solicitud HTTP (via Postman o Swagger).

El Controller la recibe y la envía al Service.

El Service gestiona los datos en memoria usando HashMap.

La respuesta se devuelve en formato JSON.

🏁 Conclusión

✅ CRUD funcional 100% en memoria
✅ Arquitectura en capas
✅ API REST documentada con Swagger
✅ Ideal para prácticas, demos o evaluación académica

👨‍💻 Integrantes:

Velarde Robles Francisco Xavier Leon  
Roman Huaman Josled Luis Antonio 
Peña Chavez Gissel Melani  
Osorio Guzman Jose Luis 
Colina Martin Jesus Gabriel

🗓️ 07 de Noviembre 2025




