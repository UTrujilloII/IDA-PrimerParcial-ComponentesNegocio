# Sistema de Gestión de Solicitudes de Soporte Técnico

[![Autor](https://img.shields.io/badge/Autor-Jonathan%20Jimenez%20Rojas-blue)](https://github.com/vansfanelx)
[![GitHub](https://img.shields.io/badge/GitHub-vansfanelx-181717?logo=github)](https://github.com/vansfanelx)
[![Java](https://img.shields.io/badge/Java-21-orange?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.4-green?logo=springboot)](https://spring.io/projects/spring-boot)

## Descripción del Proyecto

Aplicación RESTful desarrollada con **Java 21** y **Spring Boot 3.1.4** para gestionar solicitudes de soporte técnico de una empresa de servicios tecnológicos. El sistema permite registrar, consultar, actualizar y eliminar solicitudes de soporte técnico de manera eficiente.

## Contexto

Una empresa de servicios tecnológicos necesita registrar las solicitudes de soporte técnico que reciben de sus clientes. Actualmente, las solicitudes se registran en papel o en correos desordenados, lo que ocasiona pérdida de información y retrasos en la atención. 

Esta API permite a los equipos de desarrollo backend gestionar las solicitudes de manera estructurada y eficiente.

## Características Principales

✅ **CRUD completo** para Clientes, Técnicos y Solicitudes  
✅ **Validaciones robustas** usando anotaciones Jakarta Validation (`@Valid`, `@NotNull`, etc.)  
✅ **Manejo avanzado de excepciones** con códigos HTTP correctos (404, 409, 400, 500)  
✅ **Excepciones personalizadas** (`ResourceNotFoundException`, `DuplicateResourceException`, `BusinessRuleException`)  
✅ **Mensajes de error descriptivos** en formato JSON estándar  
✅ **Respuestas de éxito con mensajes** (ej: "Cliente eliminado exitosamente")  
✅ **Normalización de datos** (emails en minúsculas, códigos sin espacios)  
✅ **Validación de existencia** en operaciones de búsqueda relacionadas  
✅ **Documentación automática** con Swagger/OpenAPI 3  
✅ **Arquitectura en capas** (Controller, Service, Repository, Model, DTO, Mapper, Exception)  
✅ **Base de datos H2** en memoria para desarrollo  
✅ **Datos de ejemplo** precargados al inicio  
✅ **Buenas prácticas** de código Java y Spring Boot  

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.1.4**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **H2 Database** (en memoria)
- **Maven** (gestor de dependencias)
- **Springdoc OpenAPI 2.1.0** (documentación API)

## Estructura del Proyecto

```
src/main/java/com/tienda/appclient/
├── config/
│   └── OpenApiConfig.java          # Configuración de Swagger/OpenAPI
├── controller/
│   ├── ClienteController.java      # Endpoints REST para Clientes
│   ├── TecnicoController.java      # Endpoints REST para Técnicos
│   └── SolicitudController.java    # Endpoints REST para Solicitudes
├── dto/
│   ├── ClienteDTO.java             # DTO con validaciones para Cliente
│   ├── TecnicoDTO.java             # DTO con validaciones para Técnico
│   ├── SolicitudDTO.java           # DTO con validaciones para Solicitud
│   └── SuccessResponse.java        # DTO para respuestas de éxito
├── exception/
│   ├── ApiError.java               # Modelo de error estándar
│   ├── ResourceNotFoundException.java      # Excepción 404 Not Found
│   ├── DuplicateResourceException.java     # Excepción 409 Conflict
│   ├── BusinessRuleException.java          # Excepción 400 Bad Request
│   └── RestExceptionHandler.java  # Manejador global (@ControllerAdvice)
├── mapper/
│   ├── ClienteMapper.java          # Conversión Entity ↔ DTO
│   ├── TecnicoMapper.java          # Conversión Entity ↔ DTO
│   └── SolicitudMapper.java        # Conversión Entity ↔ DTO
├── model/
│   ├── Cliente.java                # Entidad JPA Cliente
│   ├── Tecnico.java                # Entidad JPA Técnico
│   └── Solicitud.java              # Entidad JPA Solicitud (+ enums)
├── repository/
│   ├── ClienteRepository.java      # Repositorio JPA para Cliente
│   ├── TecnicoRepository.java      # Repositorio JPA para Técnico
│   └── SolicitudRepository.java    # Repositorio JPA para Solicitud
├── service/
│   ├── ClienteService.java         # Interfaz de servicio
│   ├── TecnicoService.java         # Interfaz de servicio
│   ├── SolicitudService.java       # Interfaz de servicio
│   └── impl/
│       ├── ClienteServiceImpl.java # Lógica de negocio + validaciones
│       ├── TecnicoServiceImpl.java # Lógica de negocio + normalización
│       └── SolicitudServiceImpl.java # Lógica de negocio + reglas
├── AppClientApplication.java       # Clase principal Spring Boot
└── BootstrapData.java              # Carga de datos de ejemplo

Archivos raíz del proyecto:
├── pom.xml                          # Configuración Maven
├── README.md                        # Este archivo
├── GUIA_POSTMAN.md                  # Guía de uso con Postman
├── GUIA_SWAGGER.md                  # Guía completa de Swagger/OpenAPI
├── GUIA_ENTENDIMIENTO_CODIGO.md     # Explicación detallada del código y arquitectura
├── BASE_DE_DATOS_EXPLICACION.md     # Explicación de H2 en memoria
├── MANEJO_ERRORES.md                # Documentación de manejo de errores
├── MENSAJES_DELETE.md               # Documentación sobre DELETE
├── FIX_ACTUALIZACION_TECNICO.md     # Fix de actualización de técnicos
├── RESUMEN_MANEJO_ERRORES_COMPLETO.md # Resumen completo de errores
├── SOLUCION_ERROR_ENCODING.md       # Solución a error de encoding
├── SOLUCION_SWAGGER_404.md          # Solución a Swagger 404
├── SOLUCION_OPENAPI_404.md          # Solución a OpenAPI 404
├── COMO_INICIAR_APLICACION.md       # Instrucciones completas de inicio
├── INFORME_TECNICO.md               # Informe técnico del proyecto
├── CUMPLIMIENTO_CRITERIOS.md        # Cumplimiento de criterios
└── App-Client-API.postman_collection.json # Colección Postman
```

## Requisitos Previos

### 1. Java Development Kit (JDK) 21

**Descargar e instalar:**
- [Adoptium Temurin JDK 21](https://adoptium.net/)
- [Oracle JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Amazon Corretto 21](https://aws.amazon.com/corretto/)

**Verificar instalación:**
```cmd
java -version
```

**Configurar variable de entorno:**
- `JAVA_HOME` = `C:\Program Files\Java\jdk-21`
- Agregar `%JAVA_HOME%\bin` al `PATH`

### 2. Apache Maven

#### Opción A: Instalación con Chocolatey (recomendado)

```powershell
# Abrir PowerShell como Administrador
choco install maven -y
```

#### Opción B: Instalación Manual

1. Descargar desde [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
2. Descomprimir en `C:\Program Files\Apache\maven-3.9.x`
3. Configurar variables de entorno:
   - `MAVEN_HOME` = `C:\Program Files\Apache\maven-3.9.x`
   - Agregar `%MAVEN_HOME%\bin` al `PATH`

**Verificar instalación:**
```cmd
mvn -v
```

## Instalación y Ejecución

### 1. Clonar o descargar el proyecto

```cmd
cd D:\java_aplicaciones\app-client
```

### 2. Compilar el proyecto

```cmd
mvn clean install
```

### 3. Ejecutar la aplicación

```cmd
mvn spring-boot:run
```

La aplicación se ejecutará en: **http://localhost:8080**

### 4. Acceder a la documentación Swagger

Abrir en el navegador:
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

## 📖 Cómo Usar Springdoc OpenAPI (Swagger)

### ¿Qué es Swagger/OpenAPI?

Swagger es una herramienta que genera **documentación interactiva automática** de tu API REST. Te permite:
- ✅ Ver todos los endpoints disponibles
- ✅ Probar las APIs directamente desde el navegador
- ✅ Ver los modelos de datos (request/response)
- ✅ Conocer los códigos de respuesta HTTP
- ✅ Validar parámetros y cuerpos de petición

### Acceso a la Documentación

Una vez que la aplicación esté ejecutándose, accede a:

#### 1. Interfaz Swagger UI (Interactiva)
```
http://localhost:8080/swagger-ui/index.html
```

Aquí verás:
- 📂 **Secciones por recurso**: Clientes, Técnicos, Solicitudes
- 📋 **Listado de endpoints** con método HTTP y descripción
- 🧪 **Botón "Try it out"** para probar cada endpoint
- 📊 **Esquemas de datos** (modelos JSON)

#### 2. Especificación OpenAPI (JSON)
```
http://localhost:8080/v3/api-docs
```

Devuelve la especificación completa en formato JSON (útil para importar en Postman/Insomnia).

### Cómo Probar un Endpoint en Swagger

#### Ejemplo: Crear un Cliente

1. **Abrir Swagger UI**: http://localhost:8080/swagger-ui/index.html

2. **Buscar la sección "Clientes"** y expandirla

3. **Seleccionar** `POST /api/clientes` → Click en "Try it out"

4. **Editar el JSON** en el campo "Request body":
   ```json
   {
     "dni": "12345678",
     "apellidoPaterno": "García",
     "apellidoMaterno": "López",
     "nombres": "Juan Carlos",
     "fechaNacimiento": "1990-05-15"
   }
   ```

5. **Click en "Execute"**

6. **Ver la respuesta**:
   - **Código HTTP**: 201 Created
   - **Response body**: Cliente creado con su ID generado
   - **Response headers**: Content-Type, etc.

#### Ejemplo: Listar Técnicos Activos

1. **Buscar** `GET /api/tecnicos/activos`

2. **Click en "Try it out"** → "Execute"

3. **Ver respuesta**: Array de técnicos activos

#### Ejemplo: Asignar Técnico a Solicitud

1. **Buscar** `PATCH /api/solicitudes/{id}/asignar/{tecnicoId}`

2. **Click en "Try it out"**

3. **Ingresar parámetros**:
   - `id`: 1 (ID de la solicitud)
   - `tecnicoId`: 2 (ID del técnico)

4. **Execute** → Ver respuesta con técnico asignado

### Ventajas de Usar Swagger UI

| Ventaja | Descripción |
|---------|-------------|
| 🚀 **Rápido** | Prueba endpoints sin configurar Postman/Insomnia |
| 📖 **Documentación viva** | Siempre actualizada con el código |
| 🧪 **Validación en tiempo real** | Ve errores de validación inmediatamente |
| 📊 **Visualización de modelos** | Entiende la estructura de datos fácilmente |
| 🔍 **Exploración** | Descubre todos los endpoints disponibles |
| 💾 **Exportable** | Descarga la spec OpenAPI para otras herramientas |

### Importar a Postman desde OpenAPI

1. **Copiar URL**: http://localhost:8080/v3/api-docs

2. **Abrir Postman** → "Import" → "Link"

3. **Pegar URL** → "Continue" → "Import"

4. ✅ **Listo**: Toda la colección se importa automáticamente

### Anotaciones Swagger Usadas

La documentación se genera automáticamente gracias a estas anotaciones:

```java
// En controllers
@Tag(name = "Clientes", description = "API para gestión de clientes")
@Operation(summary = "Crear nuevo cliente")

// En DTOs
@Schema(description = "DNI del cliente", example = "12345678")

// En modelos
@Schema(description = "Cliente del sistema de soporte técnico")
```

### Configuración de OpenAPI

La configuración se encuentra en `Config.java`:

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("API de Gestión de Solicitudes de Soporte Técnico")
            .version("1.0.0")
            .description("API RESTful para gestionar solicitudes...")
            .contact(new Contact()
                .name("Equipo de Desarrollo Backend")
                .email("soporte@tienda.com")));
}
```

### Ejemplos de Uso Común

#### 1. Probar Validaciones

**Endpoint**: `POST /api/clientes`

**Body inválido** (falta DNI):
```json
{
  "nombres": "Juan"
}
```

**Respuesta esperada**: 400 Bad Request con errores de validación

#### 2. Probar Errores 404

**Endpoint**: `GET /api/clientes/999`

**Respuesta esperada**: 404 Not Found

#### 3. Probar Duplicados

**Endpoint**: `POST /api/tecnicos`

**Body** (email duplicado):
```json
{
  "codigo": "TEC-005",
  "email": "maria.rodriguez@ejemplo.com",
  ...
}
```

**Respuesta esperada**: 409 Conflict

### Personalizar la Documentación

Para agregar más información a tus endpoints:

```java
@Operation(
    summary = "Crear nuevo cliente",
    description = "Registra un nuevo cliente en el sistema. El DNI debe ser único.",
    responses = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "409", description = "DNI ya existe")
    }
)
@PostMapping
public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO dto) {
    // ...
}
```

### URLs Útiles

| URL | Descripción |
|-----|-------------|
| http://localhost:8080/swagger-ui/index.html | Interfaz interactiva |
| http://localhost:8080/v3/api-docs | Especificación JSON |
| http://localhost:8080/v3/api-docs.yaml | Especificación YAML |

📄 **Ver guía completa paso a paso**: `GUIA_SWAGGER.md`

---

## Endpoints de la API

### Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes` | Listar todos los clientes |
| GET | `/api/clientes/{id}` | Obtener cliente por ID |
| POST | `/api/clientes` | Crear nuevo cliente |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### Técnicos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/tecnicos` | Listar todos los técnicos |
| GET | `/api/tecnicos/{id}` | Obtener técnico por ID |
| GET | `/api/tecnicos/codigo/{codigo}` | Obtener técnico por código |
| GET | `/api/tecnicos/activos` | Listar técnicos activos |
| GET | `/api/tecnicos/especialidad/{especialidad}` | Buscar por especialidad |
| POST | `/api/tecnicos` | Crear nuevo técnico |
| PUT | `/api/tecnicos/{id}` | Actualizar técnico |
| DELETE | `/api/tecnicos/{id}` | Eliminar técnico |

### Solicitudes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/solicitudes` | Listar todas las solicitudes |
| GET | `/api/solicitudes/{id}` | Obtener solicitud por ID |
| GET | `/api/solicitudes/codigo/{codigo}` | Obtener por código |
| GET | `/api/solicitudes/estado/{estado}` | Filtrar por estado |
| GET | `/api/solicitudes/prioridad/{prioridad}` | Filtrar por prioridad |
| GET | `/api/solicitudes/cliente/{clienteId}` | Solicitudes de un cliente |
| GET | `/api/solicitudes/tecnico/{tecnicoId}` | Solicitudes de un técnico |
| GET | `/api/solicitudes/sin-asignar` | Solicitudes sin asignar |
| POST | `/api/solicitudes` | Crear nueva solicitud |
| PUT | `/api/solicitudes/{id}` | Actualizar solicitud |
| PATCH | `/api/solicitudes/{id}/asignar/{tecnicoId}` | Asignar técnico |
| PATCH | `/api/solicitudes/{id}/estado?estado={estado}` | Cambiar estado |
| DELETE | `/api/solicitudes/{id}` | Eliminar solicitud |

### Estados de Solicitud

- `PENDIENTE` - Recién creada, sin asignar
- `ASIGNADA` - Asignada a un técnico
- `EN_PROCESO` - El técnico está trabajando en ella
- `RESUELTA` - Solucionada
- `CERRADA` - Finalizada y cerrada
- `CANCELADA` - Cancelada

### Prioridades

- `BAJA`
- `MEDIA`
- `ALTA`
- `URGENTE`

## Ejemplos de Uso con Postman

### Crear un Cliente

**POST** `http://localhost:8080/api/clientes`

```json
{
  "dni": "45678912",
  "apellidoPaterno": "Gonzalez",
  "apellidoMaterno": "Ramirez",
  "nombres": "Sofia",
  "fechaNacimiento": "1995-07-15"
}
```

### Crear un Técnico

**POST** `http://localhost:8080/api/tecnicos`

```json
{
  "codigo": "TEC-004",
  "nombres": "Roberto",
  "apellidos": "Castillo",
  "email": "roberto.castillo@soporte.com",
  "especialidad": "Seguridad Informática",
  "activo": true
}
```

### Crear una Solicitud

**POST** `http://localhost:8080/api/solicitudes`

```json
{
  "codigo": "SOL-2025-005",
  "asunto": "Problema con impresora",
  "descripcion": "La impresora del área de administración no imprime correctamente",
  "clienteId": 1,
  "prioridad": "MEDIA",
  "estado": "PENDIENTE"
}
```

### Asignar Técnico a Solicitud

**PATCH** `http://localhost:8080/api/solicitudes/1/asignar/2`

### Cambiar Estado de Solicitud

**PATCH** `http://localhost:8080/api/solicitudes/1/estado?estado=EN_PROCESO`

## Validaciones Implementadas

### ClienteDTO
- `dni`: Obligatorio, 8-12 caracteres, solo letras, números y guiones
- `apellidoPaterno`: Obligatorio, máx 100 caracteres
- `nombres`: Obligatorio, máx 200 caracteres
- `fechaNacimiento`: Obligatoria, no puede ser futura

### TecnicoDTO
- `codigo`: Obligatorio, 3-20 caracteres, solo mayúsculas, números y guiones
- `nombres`: Obligatorio, máx 100 caracteres
- `apellidos`: Obligatorio, máx 100 caracteres
- `email`: Obligatorio, formato válido
- `especialidad`: Obligatoria, máx 100 caracteres

### SolicitudDTO
- `codigo`: Obligatorio, 5-30 caracteres
- `asunto`: Obligatorio, 5-200 caracteres
- `descripcion`: Obligatoria, 10-2000 caracteres
- `clienteId`: Obligatorio
- `estado`: Obligatorio
- `prioridad`: Obligatoria

## Manejo de Errores

La aplicación implementa un sistema robusto de manejo de errores con excepciones personalizadas y códigos HTTP apropiados:

### Tipos de Excepciones

| Excepción | Código HTTP | Cuándo se lanza |
|-----------|-------------|----------------|
| `ResourceNotFoundException` | 404 Not Found | Recurso no encontrado |
| `DuplicateResourceException` | 409 Conflict | Datos únicos duplicados (DNI, email, código) |
| `BusinessRuleException` | 400 Bad Request | Regla de negocio violada |
| `MethodArgumentNotValidException` | 400 Bad Request | Validación de campos fallida |
| `RuntimeException` | 500 Internal Server Error | Error interno del servidor |

### Formato de Respuesta de Error

Todos los errores devuelven una respuesta JSON estándar:

```json
{
  "message": "Recurso no encontrado",
  "details": [
    "Cliente no encontrado con id: 999"
  ]
}
```

### Ejemplos de Respuestas

**Recurso no encontrado (404):**
```json
{
  "message": "Recurso no encontrado",
  "details": ["Técnico no encontrado con ID: 10"]
}
```

**Recurso duplicado (409):**
```json
{
  "message": "Recurso duplicado",
  "details": ["Ya existe un técnico con el email: maria@ejemplo.com"]
}
```

**Validación fallida (400):**
```json
{
  "message": "Validación fallida",
  "details": [
    "dni: DNI es obligatorio",
    "email: Email debe ser válido"
  ]
}
```

**Regla de negocio violada (400):**
```json
{
  "message": "Regla de negocio violada",
  "details": ["No se puede asignar: el técnico no está activo"]
}
```

**Operación exitosa (200):**
```json
{
  "message": "Cliente eliminado exitosamente"
}
```

### Validaciones Automáticas

- **Existencia de recursos**: Valida que clientes/técnicos existan antes de operaciones relacionadas
- **Unicidad**: DNI, email, código único por entidad
- **Normalización**: Emails en minúsculas, códigos sin espacios
- **Reglas de negocio**: Técnico debe estar activo para asignación

📄 Ver documentación completa en: `MANEJO_ERRORES.md`

## Acceso a la Consola H2

Para ver la base de datos en memoria:

**URL:** http://localhost:8080/h2-console

**Configuración:**
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: *(dejar vacío)*

## Datos de Ejemplo Precargados

Al iniciar la aplicación se cargan automáticamente:
- 3 Clientes
- 3 Técnicos
- 4 Solicitudes de ejemplo

## Pruebas con Postman

Se incluye una colección completa de Postman (`App-Client-API.postman_collection.json`) con los siguientes casos de prueba:

### 1. Clientes
- ✅ Crear cliente
- ✅ Listar todos los clientes
- ✅ Obtener cliente por ID
- ✅ Actualizar cliente
- ✅ Eliminar cliente (con mensaje de confirmación)
- ❌ Validación de campos obligatorios (400)
- ❌ DNI duplicado (409)
- ❌ Cliente no encontrado (404)

### 2. Técnicos
- ✅ Crear técnico
- ✅ Listar todos los técnicos
- ✅ Listar técnicos activos
- ✅ Buscar por código
- ✅ Buscar por especialidad
- ✅ Actualizar técnico (sin error de duplicado propio)
- ✅ Eliminar técnico (con mensaje de confirmación)
- ❌ Código duplicado (409)
- ❌ Email duplicado (409)
- ❌ Técnico no encontrado (404)
- ❌ Validación de formato de email (400)

### 3. Solicitudes
- ✅ Crear solicitud
- ✅ Listar todas las solicitudes
- ✅ Listar solicitudes por estado
- ✅ Listar solicitudes por prioridad
- ✅ Listar solicitudes de un cliente
- ✅ Listar solicitudes de un técnico
- ✅ Listar solicitudes sin asignar
- ✅ Asignar técnico a solicitud
- ✅ Cambiar estado a "EN_PROCESO"
- ✅ Cambiar estado a "RESUELTA"
- ✅ Actualizar solicitud
- ✅ Eliminar solicitud (con mensaje de confirmación)
- ❌ Código duplicado (409)
- ❌ Cliente no encontrado (404)
- ❌ Técnico no encontrado (404)
- ❌ Solicitud no encontrada (404)
- ❌ Asignar técnico inactivo (400)
- ❌ Validación de campos obligatorios (400)

### 4. Casos de Prueba Especiales
- 🔍 Buscar solicitudes de cliente inexistente → 404
- 🔍 Buscar solicitudes de técnico inexistente → 404
- 🔄 Actualizar técnico con mismo email (variando mayúsculas) → 200
- 🔄 Actualizar solicitud con mismo código → 200
- ❌ Intentar usar email de otro técnico → 409

📄 Ver guía completa en: `GUIA_POSTMAN.md`

## Roles del Equipo de Desarrollo

- **Backend Developer 1:** Diseño de modelos, DTOs y repositorios
- **Backend Developer 2:** Implementación de servicios y controladores
- **Backend Developer 3:** Validaciones, excepciones y documentación

## Mejoras Futuras

- [ ] Autenticación y autorización con Spring Security
- [ ] Paginación y ordenamiento en listados
- [ ] Filtros avanzados de búsqueda
- [ ] Notificaciones por email
- [ ] Historial de cambios de estado
- [ ] Reportes y estadísticas
- [ ] Archivos adjuntos en solicitudes
- [ ] Base de datos persistente (PostgreSQL/MySQL)

## Contacto y Soporte

### Autor del Proyecto

**Jonathan Jose Jimenez Rojas**  
*Ingeniero de Sistemas*

- 🔗 GitHub: [@vansfanelx](https://github.com/vansfanelx)
- 📧 Email: soporte@tienda.com

### Equipo de Desarrollo Backend

Para consultas sobre el proyecto, contactar al equipo de desarrollo backend.

---

## ⚠️ Notas Importantes

### Base de Datos

**Este proyecto usa H2 Database completamente EN MEMORIA:**

- ✅ No requiere instalación de servidor de base de datos
- ✅ Los datos se cargan automáticamente al iniciar (BootstrapData.java)
- ❌ Los datos SE PIERDEN al cerrar la aplicación
- 🔄 Cada reinicio vuelve a cargar los datos de ejemplo
- 📊 Acceso a H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:clientesdb`
  - User: `sa`
  - Password: (vacío)

**¿Por qué en memoria?**
- Perfecto para desarrollo y aprendizaje
- Sin configuración compleja
- Siempre limpio al reiniciar
- Rápido y sin dependencias externas

📄 **Más información:** Ver `BASE_DE_DATOS_EXPLICACION.md`

### Comandos Maven

**No se incluyen scripts .bat** - Usa comandos Maven directamente:

```cmd
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run

# Ejecutar tests
mvn test

# Compilar sin tests
mvn clean install -DskipTests
```

---

**Versión:** 1.0  
**Fecha:** Noviembre 2025  
**Licencia:** MIT

# soporte_tecnico
#   s o p o r t e _ t e c n i c o _ j a v a 
 
 #   s o p o r t e _ t e c n i c o _ j a v a 
 
 
