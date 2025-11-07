# API RESTful de Soporte Técnico

## Descripción
Esta es una **API RESTful desarrollada en Java con Spring Boot** para gestionar solicitudes de soporte técnico.  
Permite registrar, consultar, actualizar y eliminar **clientes, técnicos y solicitudes**, usando estructuras de datos en memoria (`List` y `Map`) durante la ejecución.

El sistema reemplaza el registro manual en papel o correos desordenados, asegurando una gestión rápida y confiable de las solicitudes de soporte.

---

## Estructura del Proyecto

```bash
src/
├─ main/
│ ├─ java/
│ │ └─ pe.edu.idat.soporte.soportebackend/
│ │ ├─ controller/ # Endpoints RESTContrller
│ │ ├─ dto/ # (ClienteDTO, TecnicoDTO, SolicitudDTO)
│ │ ├─ model/ # Clases: Cliente, Tecnico, Solicitud
│ │ ├─ service/ # Lógica de negocio (interfaces y clases)
│ │ ├─ repository/ # Aquí se guardan y buscan los datos (simula la base de datos)
│ │ └─ exceptions/ # Manejo centralizado de errores (@RestControllerAdvice)
│ └─ resources/
│ └─ application.properties
```

### Clientes

| Método | Endpoint            | Descripción             | Body JSON ejemplo |
|--------|-------------------|-------------------------|------------------|
| GET    | `/api/cliente`      | Listar todos los clientes | - |
| GET    | `/api/cliente/{id}` | Consultar cliente por ID | - |
| POST   | `/api/cliente`      | Crear nuevo cliente      | `{"dni":"12345678","nombre":"Juan","apellido":"Perez","email":"juan@mail.com","telefono":"987654321"}` |
| PUT    | `/api/cliente/{id}` | Actualizar cliente       | Igual que POST |
| DELETE | `/api/cliente/{id}` | Eliminar cliente         | - |

### Técnicos

| Método | Endpoint            | Descripción             | Body JSON ejemplo |
|--------|-------------------|-------------------------|------------------|
| GET    | `/api/tecnico`      | Listar todos los técnicos | - |
| GET    | `/api/tecnico/{id}` | Consultar técnico por ID | - |
| POST   | `/api/tecnico`      | Crear nuevo técnico     | `{"nombre":"Ana","apellido":"Lopez","email":"ana@mail.com","telefono":"987654322","especialidad":"Redes"}` |
| PUT    | `/api/tecnico/{id}` | Actualizar técnico      | Igual que POST |
| DELETE | `/api/tecnico/{id}` | Eliminar técnico        | - |

---

### Solicitudes

| Método | Endpoint            | Descripción             | Body JSON ejemplo |
|--------|-------------------|-------------------------|------------------|
| GET    | `/api/solicitud`    | Listar todas las solicitudes | - |
| GET    | `/api/solicitud/{id}` | Consultar solicitud por ID | - |
| POST   | `/api/solicitud`    | Crear nueva solicitud   | `{"titulo":"Problema PC","descripcion":"No enciende","idCliente":1,"idTecnico":2}` |
| PUT    | `/api/solicitud/{id}` | Actualizar solicitud   | Igual que POST |
| DELETE | `/api/solicitud/{id}` | Eliminar solicitud     | - |

> Todas las entradas son validadas con **anotaciones de DTO** (`@NotBlank`, `@Size`, `@Email`, `@Pattern`, `@NotNull`).
## ⚙️ Instalación y Ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/joel-machaca/soporte-backend-ciclo4.git
cd soporte-backend-ciclo4
```

2.Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

4.Acceder a la API
```bash
http://localhost:8080/api/...
```

## Pruebas con Postman

Importar colección: Postman_collection.json

Ejemplos:
```bash
# Crear cliente

POST http://localhost:8080/api/cliente
Body:
{
"dni":"12345678",
"nombre":"Juan",
"apellido":"Perez",
"email":"juan@mail.com",
"telefono":"987654321"
}

# Crear técnico
POST http://localhost:8080/api/tecnico
Body:
{
"nombre":"Ana",
"apellido":"Lopez",
"email":"ana@mail.com",
"telefono":"987654322",
"especialidad":"Redes"
}

# Crear solicitud
POST http://localhost:8080/api/solicitud
Body:
{
"titulo":"Problema PC",
"descripcion":"No enciende",
"idCliente":1,
"idTecnico":1
}
```

## Documentación

Swagger UI disponible en:

```bash
http://localhost:8080/swagger-ui.html
```

Incluye descripción de endpoints, parámetros, ejemplos de request/response y códigos de estado HTTP.

## Persistencia Simulada

- Datos almacenados en listas y mapas en memoria.

- Permite CRUD completo sin base de datos.

- Los datos se eliminan al cerrar la aplicación.

## Tecnologías

- Java 17

- Spring Boot 3

- Maven

- Swagger (OpenAPI)

- Postman