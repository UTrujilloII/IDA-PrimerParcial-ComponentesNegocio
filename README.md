# Solution Empresa - Grupo 09

**Proyecto de Desarrollo de Componentes del Negocio**  
_4° Ciclo - IDAT_

---

## Descripción

Esta aplicación implementa una **API RESTful** para la gestión de información empresarial.  
Se desarrolló con **Spring Boot**, siguiendo buenas prácticas de arquitectura por capas (Controller → Service → Repository → DTO → Model) **.

---

## Tecnologías

## Tecnologías

| Tecnología / Herramienta      | Versión / Uso |
|-------------------------------|---------------|
| Java                          | 21            | Lenguaje de programación principal |
| Spring Boot                   | 3.5.7         | Framework para desarrollo web y APIs REST |
| Maven                         | 3.x           | Gestión de dependencias y construcción del proyecto |
| Swagger / Springdoc           | 2.1.0         | Documentación y prueba de APIs REST |
| Jakarta Validation            | Última        | Validación de datos en DTOs (ej. @NotNull, @Size, @Pattern) |
| DTOs (Data Transfer Objects)  | N/A           | Transferencia de datos entre capas |
| Service / ServiceImpl         | N/A           | Lógica de negocio y mantenimiento de solicitudes |
| Controller / REST API         | N/A           | Exposición de endpoints para consumo de la API |
| Almacenamiento en memoria       | N/A           | Guardado de datos temporal mientras corre la aplicación (sin base de datos) |


---

## 📂 Estructura del Proyecto
app-SolutionEmpresa/
├─ src/
│ └─ main/
│ ├─ java/pe/idat/backend/solutionEmpresa/app_SolutionEmpresa/
│ │ ├─ controller/ → Endpoints REST
│ │ ├─ dto/ → Objetos de transferencia de datos
│ │ ├─ model/ → Entidades del negocio
│ │ ├─ repository/ → Repositorios de datos
│ │ ├─ service/ → Lógica de negocio
│ │ └─ AppSolutionEmpresaApplication.java → Clase principal
│ └─ resources/
│ └─ application.properties
├─ HELP.md
├─ pom.xml
└─ README.md


## Cómo ejecutar `app-SolutionEmpresa`

1. **Clonar el repositorio**
```bash
git clone <URL_DEL_REPOSITORIO>
cd app-SolutionEmpresa

Abrir en un IDE

Se recomienda IntelliJ IDEA.

Maven descargará automáticamente las dependencias.

Correr la aplicación

mvn spring-boot:run

También puedes ejecutar la clase principal con @SpringBootApplication desde tu IDE.

Probar la API

Aplicación: http://localhost:8080

Swagger: http://localhost:8080/swagger-ui/index.html