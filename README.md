# API de Gestión de Solicitudes de Soporte (Evaluación Parcial)

Este proyecto es una API RESTful desarrollada con Spring Boot para la gestión de solicitudes de soporte técnico. El objetivo principal es demostrar una arquitectura de software limpia y desacoplada, respetando las restricciones de la evaluación.

La principal característica de esta API es que **no utiliza una base de datos tradicional**. En su lugar, simula la persistencia de datos en memoria utilizando colecciones de Java (`Map`) dentro de una capa de Repositorio simulada, garantizando que los datos persistan únicamente mientras la aplicación está en ejecución.

## 🏛️ Arquitectura

El proyecto sigue una arquitectura estricta en capas para la separación de responsabilidades:

* **`controller`**: Maneja las peticiones HTTP (`@RestController`), valida la entrada y se comunica **únicamente con DTOs**.
* **`service`**: Contiene la lógica de negocio (orquestación). Es el intermediario entre el controlador y el repositorio.
* **`repository`**: Capa de acceso a datos simulada (`@Repository`). Es la única capa que maneja la "base de datos" en memoria (el `Map` y `AtomicLong`).
* **`model`**: Contiene las entidades de dominio internas (POJOs limpios, sin validaciones).
* **`dto`**: (Data Transfer Objects) Define los "contratos" de la API para entrada (`RequestDto`) y salida (`ResponseDto`). Aquí viven las validaciones.
* **`exception`**: Manejo de errores centralizado (`@ControllerAdvice`) para devolver respuestas consistentes (400, 404).

## ✨ Tecnologías Utilizadas

* **Java 20+**
* **Spring Boot 3+**
* **Spring Web**: Para la creación de la API RESTful.
* **Spring Validation**: Para las validaciones (`@Valid`, `@NotBlank`, etc.).
* **Lombok**: Para reducir el código boilerplate (getters, setters, etc.).
* **Maven**: Para la gestión de dependencias.
* **Springdoc (Swagger)**: Para la documentación interactiva de la API.

## 🚀 Instalación y Ejecución

Sigue estos pasos para levantar el proyecto localmente.

### 1. Prerrequisitos

* JDK 17 o superior.
* Apache Maven 3.6+
* Un IDE de tu preferencia (Ej. IntelliJ IDEA, VSCode, Eclipse) o la línea de comandos.

### 2. Instalación

Clona este repositorio en tu máquina local:

```bash
git clone [PEGA AQUÍ LA URL DE TU REPOSITORIO GIT]
cd [NOMBRE-DE-LA-CARPETA-DEL-PROYECTO]