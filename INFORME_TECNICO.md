# INFORME TÉCNICO
## Sistema de Gestión de Solicitudes de Soporte Técnico

---

## 1. DESCRIPCIÓN DEL PROYECTO

### 1.1 Contexto
Una empresa de servicios tecnológicos necesitaba un sistema para registrar las solicitudes de soporte técnico que reciben de sus clientes. Anteriormente, las solicitudes se registraban en papel o en correos desordenados, ocasionando pérdida de información y retrasos en la atención.

### 1.2 Solución Propuesta
Desarrollo de una **API RESTful** usando **Java 21** y **Spring Boot 3.1.4** que permite registrar, consultar, actualizar y eliminar solicitudes de soporte técnico de manera eficiente.

### 1.3 Objetivo
Permitir a los equipos de desarrollo backend gestionar las solicitudes de soporte técnico de manera estructurada, con validaciones de datos, manejo centralizado de excepciones y documentación automática.

---

## 2. ARQUITECTURA DEL SISTEMA

### 2.1 Patrón de Arquitectura
Se implementó una **arquitectura en capas (Layered Architecture)** siguiendo las mejores prácticas de Spring Boot:

```
┌─────────────────────────────────────┐
│         CAPA PRESENTACIÓN           │
│     (Controllers / REST API)        │
│  ClienteController, TecnicoController│
│      SolicitudController            │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         CAPA DE NEGOCIO             │
│         (Services / DTOs)           │
│  ClienteService, TecnicoService     │
│      SolicitudService               │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      CAPA DE PERSISTENCIA           │
│       (Repositories / JPA)          │
│  ClienteRepository, TecnicoRepository│
│      SolicitudRepository            │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       BASE DE DATOS (H2)            │
│       En Memoria (desarrollo)       │
└─────────────────────────────────────┘
```

### 2.2 Componentes Transversales

- **Mappers:** Conversión entre Entidades JPA y DTOs
- **Exception Handlers:** Manejo centralizado de excepciones con `@ControllerAdvice`
- **Validation:** Validaciones declarativas con Jakarta Bean Validation
- **OpenAPI/Swagger:** Documentación automática de la API

---

## 3. MODELADO DE CLASES

### 3.1 Entidades del Dominio

#### Cliente
```java
- id: Integer (PK)
- dni: String (UNIQUE, NOT NULL)
- apellidoPaterno: String (NOT NULL)
- apellidoMaterno: String
- nombres: String (NOT NULL)
- fechaNacimiento: LocalDate
```

#### Técnico
```java
- id: Integer (PK)
- codigo: String (UNIQUE, NOT NULL)
- nombres: String (NOT NULL)
- apellidos: String (NOT NULL)
- email: String (UNIQUE, NOT NULL)
- especialidad: String (NOT NULL)
- activo: Boolean (DEFAULT true)
```

#### Solicitud
```java
- id: Integer (PK)
- codigo: String (UNIQUE, NOT NULL)
- asunto: String (NOT NULL)
- descripcion: String (TEXT, NOT NULL)
- cliente: Cliente (FK, NOT NULL)
- tecnico: Tecnico (FK, NULLABLE)
- estado: EstadoSolicitud (ENUM, NOT NULL)
- prioridad: PrioridadSolicitud (ENUM, NOT NULL)
- fechaCreacion: LocalDateTime (NOT NULL)
- fechaAsignacion: LocalDateTime
- fechaResolucion: LocalDateTime
- observaciones: String (TEXT)
```

### 3.2 Enumeraciones

**EstadoSolicitud:**
- PENDIENTE
- ASIGNADA
- EN_PROCESO
- RESUELTA
- CERRADA
- CANCELADA

**PrioridadSolicitud:**
- BAJA
- MEDIA
- ALTA
- URGENTE

### 3.3 Diagrama de Relaciones

```
┌──────────────┐         1       ┌──────────────┐
│   Cliente    │◄────────────────┤  Solicitud   │
│              │                 │              │
└──────────────┘                 └──────┬───────┘
                                        │ 0..1
                                        │
                                        │
                                 ┌──────▼───────┐
                                 │   Técnico    │
                                 │              │
                                 └──────────────┘
```

- Un **Cliente** puede tener muchas **Solicitudes** (1:N)
- Una **Solicitud** puede estar asignada a un **Técnico** (0..1:N)
- Un **Técnico** puede atender muchas **Solicitudes** (1:N)

---

## 4. ESTRUCTURA DEL PROYECTO

### 4.1 Organización de Paquetes

```
com.tienda.appclient/
├── config/              # Configuraciones (OpenAPI)
├── controller/          # Controladores REST
├── dto/                 # Data Transfer Objects
├── exception/           # Manejo de excepciones
├── mapper/              # Conversores Entity ↔ DTO
├── model/               # Entidades JPA
├── repository/          # Repositorios Spring Data JPA
└── service/             # Lógica de negocio
    └── impl/            # Implementaciones de servicios
```

### 4.2 Separación de Responsabilidades

| Capa | Responsabilidad |
|------|----------------|
| **Controller** | Recibir peticiones HTTP, validar DTOs, invocar servicios, devolver respuestas |
| **Service** | Lógica de negocio, validaciones complejas, orquestación de operaciones |
| **Repository** | Acceso a datos, consultas a base de datos |
| **Model** | Representación de entidades del dominio |
| **DTO** | Transferencia de datos, validaciones de entrada |
| **Mapper** | Conversión entre modelos y DTOs |

---

## 5. API REST - ENDPOINTS

### 5.1 Clientes (`/api/clientes`)

| Método | Endpoint | Descripción | Código |
|--------|----------|-------------|--------|
| GET | `/api/clientes` | Listar todos | 200 |
| GET | `/api/clientes/{id}` | Obtener por ID | 200, 404 |
| POST | `/api/clientes` | Crear cliente | 201, 400 |
| PUT | `/api/clientes/{id}` | Actualizar | 200, 400, 404 |
| DELETE | `/api/clientes/{id}` | Eliminar | 204 |

### 5.2 Técnicos (`/api/tecnicos`)

| Método | Endpoint | Descripción | Código |
|--------|----------|-------------|--------|
| GET | `/api/tecnicos` | Listar todos | 200 |
| GET | `/api/tecnicos/{id}` | Obtener por ID | 200, 404 |
| GET | `/api/tecnicos/codigo/{codigo}` | Por código | 200, 404 |
| GET | `/api/tecnicos/activos` | Solo activos | 200 |
| GET | `/api/tecnicos/especialidad/{esp}` | Por especialidad | 200 |
| POST | `/api/tecnicos` | Crear técnico | 201, 400 |
| PUT | `/api/tecnicos/{id}` | Actualizar | 200, 400, 404 |
| DELETE | `/api/tecnicos/{id}` | Eliminar | 204 |

### 5.3 Solicitudes (`/api/solicitudes`)

| Método | Endpoint | Descripción | Código |
|--------|----------|-------------|--------|
| GET | `/api/solicitudes` | Listar todas | 200 |
| GET | `/api/solicitudes/{id}` | Obtener por ID | 200, 404 |
| GET | `/api/solicitudes/codigo/{codigo}` | Por código | 200, 404 |
| GET | `/api/solicitudes/estado/{estado}` | Filtrar por estado | 200 |
| GET | `/api/solicitudes/prioridad/{prioridad}` | Filtrar por prioridad | 200 |
| GET | `/api/solicitudes/cliente/{id}` | Por cliente | 200 |
| GET | `/api/solicitudes/tecnico/{id}` | Por técnico | 200 |
| GET | `/api/solicitudes/sin-asignar` | Sin asignar | 200 |
| POST | `/api/solicitudes` | Crear solicitud | 201, 400 |
| PUT | `/api/solicitudes/{id}` | Actualizar | 200, 400, 404 |
| PATCH | `/api/solicitudes/{id}/asignar/{tecId}` | Asignar técnico | 200, 404 |
| PATCH | `/api/solicitudes/{id}/estado?estado={est}` | Cambiar estado | 200, 404 |
| DELETE | `/api/solicitudes/{id}` | Eliminar | 204 |

---

## 6. VALIDACIONES IMPLEMENTADAS

### 6.1 ClienteDTO

| Campo | Validación |
|-------|-----------|
| dni | `@NotBlank`, `@Size(8-12)`, `@Pattern([0-9A-Za-z-]+)` |
| apellidoPaterno | `@NotBlank`, `@Size(max=100)` |
| apellidoMaterno | `@Size(max=100)` |
| nombres | `@NotBlank`, `@Size(max=200)` |
| fechaNacimiento | `@NotNull`, `@PastOrPresent` |

### 6.2 TecnicoDTO

| Campo | Validación |
|-------|-----------|
| codigo | `@NotBlank`, `@Size(3-20)`, `@Pattern([A-Z0-9-]+)` |
| nombres | `@NotBlank`, `@Size(max=100)` |
| apellidos | `@NotBlank`, `@Size(max=100)` |
| email | `@NotBlank`, `@Email` |
| especialidad | `@NotBlank`, `@Size(max=100)` |

### 6.3 SolicitudDTO

| Campo | Validación |
|-------|-----------|
| codigo | `@NotBlank`, `@Size(5-30)` |
| asunto | `@NotBlank`, `@Size(5-200)` |
| descripcion | `@NotBlank`, `@Size(10-2000)` |
| clienteId | `@NotNull` |
| estado | `@NotNull` |
| prioridad | `@NotNull` |
| observaciones | `@Size(max=1000)` |

---

## 7. MANEJO DE EXCEPCIONES

### 7.1 RestExceptionHandler (`@ControllerAdvice`)

Maneja de forma centralizada todas las excepciones:

```java
- MethodArgumentNotValidException → 400 BAD REQUEST
  Respuesta: { message, errors[] }

- RuntimeException → 500 INTERNAL SERVER ERROR
  Respuesta: { message, errors[] }
```

### 7.2 Ejemplo de Respuesta de Error

```json
{
  "message": "Validación fallida",
  "errors": [
    "dni: DNI es obligatorio",
    "email: Email debe ser válido",
    "fechaNacimiento: Fecha de nacimiento no puede ser futura"
  ]
}
```

---

## 8. DOCUMENTACIÓN CON SWAGGER/OPENAPI

### 8.1 Configuración

- **Dependencia:** `springdoc-openapi-starter-webmvc-ui:2.1.0`
- **Bean personalizado:** `OpenApiConfig`
- **Anotaciones en controladores:** `@Tag`, `@Operation`

### 8.2 URLs de Acceso

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

### 8.3 Captura de Swagger UI

_(Se incluiría captura de pantalla de Swagger UI mostrando los endpoints documentados)_

---

## 9. PRUEBAS FUNCIONALES CON POSTMAN

### 9.1 Escenarios de Prueba

#### Caso 1: Crear Cliente Válido
```
POST /api/clientes
Body:
{
  "dni": "45678912",
  "apellidoPaterno": "Gonzalez",
  "nombres": "Sofia",
  "fechaNacimiento": "1995-07-15"
}

Resultado Esperado: 201 CREATED
```

#### Caso 2: Crear Cliente con DNI Inválido
```
POST /api/clientes
Body:
{
  "dni": "123",
  "apellidoPaterno": "Perez",
  "nombres": "Juan"
}

Resultado Esperado: 400 BAD REQUEST
Error: "dni: DNI debe tener entre 8 y 12 caracteres"
```

#### Caso 3: Crear Solicitud y Asignar Técnico
```
1. POST /api/solicitudes
   Body: { codigo, asunto, descripcion, clienteId, prioridad }
   → 201 CREATED

2. PATCH /api/solicitudes/1/asignar/2
   → 200 OK
   Estado cambia de PENDIENTE a ASIGNADA
   Se establece fechaAsignacion
```

#### Caso 4: Cambiar Estado de Solicitud
```
PATCH /api/solicitudes/1/estado?estado=EN_PROCESO
→ 200 OK
Estado cambia a EN_PROCESO
```

#### Caso 5: Listar Solicitudes Pendientes
```
GET /api/solicitudes/estado/PENDIENTE
→ 200 OK
Devuelve array de solicitudes con estado PENDIENTE
```

### 9.2 Colección de Postman

Se ha creado una colección con los siguientes tests:

✅ CRUD completo de Clientes (5 requests)  
✅ CRUD completo de Técnicos (8 requests)  
✅ CRUD completo de Solicitudes (13 requests)  
✅ Validación de campos obligatorios (3 requests)  
✅ Validación de formatos (email, DNI, código) (3 requests)  
✅ Flujo completo: Crear → Asignar → Cambiar Estado (1 flujo)  

**Total: 33 casos de prueba**

_(Se incluiría enlace al archivo JSON de la colección de Postman o capturas de los tests ejecutados)_

---

## 10. DATOS DE EJEMPLO PRECARGADOS

Al iniciar la aplicación, se cargan automáticamente mediante `BootstrapData`:

### 10.1 Clientes
- Juan Perez Gomez (DNI: 12345678)
- María Lopez Diaz (DNI: 87654321)
- Carlos Rodriguez Santos (DNI: 11223344)

### 10.2 Técnicos
- Pedro Ramirez (TEC-001) - Especialidad: Redes
- Ana Martinez (TEC-002) - Especialidad: Software
- Luis Fernandez (TEC-003) - Especialidad: Hardware

### 10.3 Solicitudes
- SOL-2025-001: Problema con conexión a internet (PENDIENTE, ALTA)
- SOL-2025-002: Instalación de software contable (ASIGNADA a TEC-002, MEDIA)
- SOL-2025-003: Computadora no enciende (EN_PROCESO con TEC-003, URGENTE)
- SOL-2025-004: Configuración de correo electrónico (PENDIENTE, BAJA)

---

## 11. BASE DE DATOS

### 11.1 Tecnología y Modo de Operación

**⚠️ IMPORTANTE: La base de datos es completamente EN MEMORIA**

- **Motor:** H2 Database Engine
- **Modo:** **IN-MEMORY** (jdbc:h2:**mem**:clientesdb)
- **Persistencia:** ❌ NO - Los datos se pierden al reiniciar la aplicación
- **Propósito:** Desarrollo y pruebas rápidas
- **Consola H2:** ✅ Habilitada en http://localhost:8080/h2-console

### 11.2 Características de H2 In-Memory

| Característica | Valor | Descripción |
|----------------|-------|-------------|
| **Tipo de almacenamiento** | Memoria RAM | Los datos solo existen mientras la app corre |
| **Velocidad** | ⚡ Muy alta | No hay I/O de disco |
| **Persistencia** | ❌ No | Se pierde todo al cerrar la app |
| **Ideal para** | Desarrollo, Testing | No para producción |
| **Datos iniciales** | ✅ Autocarga | Via BootstrapData.java |

### 11.3 Ciclo de Vida de los Datos

```
┌──────────────────────────────────────────────────────────┐
│  1. INICIO DE APLICACIÓN (mvn spring-boot:run)          │
│     ├─ H2 crea BD en memoria                            │
│     ├─ Hibernate crea tablas (ddl-auto=create-drop)     │
│     └─ BootstrapData carga datos de ejemplo             │
└──────────────────────────────────────────────────────────┘
              ↓
┌──────────────────────────────────────────────────────────┐
│  2. APLICACIÓN CORRIENDO                                 │
│     ├─ Los datos existen en RAM                          │
│     ├─ Puedes hacer CRUD completo                        │
│     ├─ Puedes ver datos en H2 Console                    │
│     └─ Todo funciona como BD normal                      │
└──────────────────────────────────────────────────────────┘
              ↓
┌──────────────────────────────────────────────────────────┐
│  3. CIERRE DE APLICACIÓN (Ctrl+C)                        │
│     ├─ H2 elimina la BD de memoria                       │
│     ├─ Hibernate hace DROP de todas las tablas           │
│     └─ ❌ TODOS LOS DATOS SE PIERDEN                     │
└──────────────────────────────────────────────────────────┘
              ↓
┌──────────────────────────────────────────────────────────┐
│  4. PRÓXIMO INICIO                                       │
│     └─ Vuelve al paso 1 (BD vacía, recarga datos)       │
└──────────────────────────────────────────────────────────┘
```

### 11.4 Configuración (application.properties)

```properties
# Base de Datos H2 EN MEMORIA
spring.datasource.url=jdbc:h2:mem:clientesdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
                              ^^^
                              └── "mem" = EN MEMORIA (no en disco)

spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
                              ^^^^^^^^^^^
                              └── Crea al iniciar, elimina al cerrar

spring.jpa.show-sql=true  # Muestra SQL en consola

# Consola H2 (para ver datos mientras corre)
spring.h2.console.enabled=true
```

### 11.5 ¿Por Qué En Memoria?

| Ventaja | Descripción |
|---------|-------------|
| ✅ **Sin configuración** | No requiere instalar servidor de BD |
| ✅ **Rápido** | Operaciones instantáneas en RAM |
| ✅ **Limpio** | Cada inicio es fresh start |
| ✅ **Desarrollo ágil** | Ideal para aprender y probar |
| ✅ **Sin conflictos** | No hay archivos ni bloqueos |

| Desventaja | Descripción |
|------------|-------------|
| ❌ **No persistente** | Datos desaparecen al cerrar |
| ❌ **No escalable** | Limitado por RAM |
| ❌ **No para producción** | Solo desarrollo/testing |

### 11.6 Cómo Migrar a Base de Datos Persistente (Futuro)

Si quisieras usar una BD persistente, cambiarías:

#### Opción 1: H2 en Archivo (Persistente)
```properties
# Cambiar de memoria a archivo
spring.datasource.url=jdbc:h2:file:./data/clientesdb
                              ^^^^
                              └── "file" = EN DISCO

spring.jpa.hibernate.ddl-auto=update
                              ^^^^^^
                              └── No elimina datos al cerrar
```

#### Opción 2: MySQL/PostgreSQL
```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/clientesdb
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/clientesdb
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### 11.3 Esquema Generado

```sql
CREATE TABLE clientes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  dni VARCHAR(12) UNIQUE NOT NULL,
  apellido_paterno VARCHAR(100) NOT NULL,
  apellido_materno VARCHAR(100),
  nombres VARCHAR(200) NOT NULL,
  fecha_nacimiento DATE
);

CREATE TABLE tecnicos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(20) UNIQUE NOT NULL,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  especialidad VARCHAR(100) NOT NULL,
  activo BOOLEAN DEFAULT TRUE
);

CREATE TABLE solicitudes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  codigo VARCHAR(30) UNIQUE NOT NULL,
  asunto VARCHAR(200) NOT NULL,
  descripcion TEXT NOT NULL,
  cliente_id INT NOT NULL,
  tecnico_id INT,
  estado VARCHAR(20) NOT NULL,
  prioridad VARCHAR(20) NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL,
  fecha_asignacion TIMESTAMP,
  fecha_resolucion TIMESTAMP,
  observaciones TEXT,
  FOREIGN KEY (cliente_id) REFERENCES clientes(id),
  FOREIGN KEY (tecnico_id) REFERENCES tecnicos(id)
);
```

---

## 12. ROLES DEL EQUIPO DE DESARROLLO

| Rol | Responsabilidad | Tareas Principales |
|-----|----------------|-------------------|
| **Backend Developer 1** | Diseño de datos | Modelado de clases, DTOs, entidades JPA, repositorios |
| **Backend Developer 2** | Lógica de negocio | Servicios, controladores REST, mappers |
| **Backend Developer 3** | Calidad y documentación | Validaciones, excepciones, OpenAPI, pruebas |

---

## 13. CÓDIGO FUENTE - ENLACES DEL REPOSITORIO

### 13.1 Estructura en GitHub

```
https://github.com/[usuario]/app-client/
├── README.md                    ← Instrucciones de instalación
├── INFORME_TECNICO.md           ← Este documento
├── pom.xml                      ← Dependencias Maven
├── src/
│   ├── main/
│   │   ├── java/com/tienda/appclient/
│   │   └── resources/application.properties
│   └── test/
└── postman/
    └── app-client.postman_collection.json
```

### 13.2 Permisos de Acceso

- **Lectura pública:** ✅ Activada
- **Clonación:** Disponible para el equipo docente y evaluadores

---

## 14. CONCLUSIONES Y BUENAS PRÁCTICAS APLICADAS

### 14.1 Arquitectura
✅ **Separación de responsabilidades** (Controller-Service-Repository)  
✅ **DTOs** para desacoplar la API del modelo de datos  
✅ **Mappers** para conversión limpia entre capas  

### 14.2 Validación y Manejo de Errores
✅ **Validaciones declarativas** con Jakarta Bean Validation  
✅ **Manejo centralizado de excepciones** con `@ControllerAdvice`  
✅ **Respuestas de error consistentes** en formato JSON  

### 14.3 Documentación
✅ **Swagger/OpenAPI** para documentación automática e interactiva  
✅ **Anotaciones descriptivas** en controladores (`@Tag`, `@Operation`)  

### 14.4 Código Limpio
✅ **Nombres descriptivos** en clases, métodos y variables  
✅ **Interfaces de servicio** para facilitar testing  
✅ **Inyección de dependencias** por constructor  
✅ **Sintaxis clara** siguiendo convenciones de Java  

### 14.5 Despliegue y Desarrollo
✅ **Configuración por propiedades** (application.properties)  
✅ **Datos de ejemplo** para pruebas rápidas  
✅ **Base de datos en memoria** para desarrollo ágil  
✅ **Maven** para gestión de dependencias reproducible  

---

## 15. CAPTURAS DE PANTALLA

_(Incluir aquí las capturas solicitadas)_

### 15.1 Swagger UI - Vista General
![Swagger UI General]

### 15.2 Postman - Crear Cliente Válido
![Postman - POST Cliente]

### 15.3 Postman - Error de Validación
![Postman - Validación DNI]

### 15.4 Postman - Listar Solicitudes
![Postman - GET Solicitudes]

### 15.5 Postman - Asignar Técnico
![Postman - PATCH Asignar]

### 15.6 Consola H2 - Datos en Tablas
![H2 Console - Solicitudes]

### 15.7 Estructura del Proyecto en IDE
![IntelliJ - Estructura]

---

## 16. ANEXOS

### 16.1 Dependencias Maven (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- Base de Datos -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Documentación -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.1.0</version>
    </dependency>
    
    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 16.2 Comandos de Ejecución

```bash
# Compilar proyecto
mvn clean install

# Ejecutar aplicación
mvn spring-boot:run

# Ejecutar tests
mvn test

# Generar JAR ejecutable
mvn package
java -jar target/app-client-0.0.1-SNAPSHOT.jar
```

---

**Equipo de Desarrollo Backend**  
Fecha: Noviembre 2025  
Versión del Informe: 1.0

