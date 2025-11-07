Soporte API — Spring Boot (IDAT 2025)

API REST para gestionar solicitudes de soporte técnico con validaciones avanzadas, carga masiva, documentación Swagger UI, vistas HTML (Bootstrap) y manejo global de errores.
Proyecto académico, persistencia en memoria (sin BD), arquitectura con DTO → Controller → Service → Model.


Índice
Descripción
Arquitectura
Tecnologías y dependencias
Requisitos
Ejecución
Documentación Swagger
Endpoints
Validaciones
Vistas HTML
Estructura del proyecto
Estrategia de ramas (Git)
✅
Convenciones de commits
Flujo de trabajo (Gitflow)
Etiquetas y versionado
Cómo contribuir
Licencia


Descripción

El sistema registra solicitudes de soporte con:
Cliente (nombre, correo)
Técnico (nombre, especialidad)
Título y descripción
Fecha y hora (ingresadas por el usuario, validadas)
Estado: Pendiente | En proceso | Finalizado | Cancelado
Incluye: carga individual o masiva, mensajes de error formateados, Swagger, vistas HTML con modo oscuro, y excepciones globales.


🧩 Arquitectura

controller/
├─ HomeController.java
└─ SolicitudController.java     ← Validaciones + HTML/JSON + CRUD
dto/
├─ SolicitudDTO.java            ← Sin fechaRegistro en JSON
├─ ClienteDTO.java
└─ TecnicoDTO.java
service/
├─ SolicitudService.java
└─ SolicitudServiceImpl.java    ← Memoria (sin BD)
model/
├─ Solicitud.java
├─ Cliente.java
└─ Tecnico.java
config/
└─ SwaggerConfig.java           ← OpenAPI 3
exception/
├─ GlobalExceptionHandler.java
└─ RecursoNoEncontradoException.java



🧱 Tecnologías y dependencias

Java 21/25, Maven 3.9.11, Spring Boot
spring-boot-starter-web
spring-boot-starter-thymeleaf
spring-boot-starter-validation
springdoc-openapi-starter-webmvc-ui
lombok
Bootstrap (CDN en HTML)



🧰 Requisitos

Java 21 (o 25)
Maven 3.9.11
Puerto libre 8080



▶️ Ejecución

# 1) Clonar
git clone https://github.com/tu-user/soporte-api.git
cd soporte-api

# 2) Verificar Maven/Java
mvn -version

# 3) Ejecutar
mvn spring-boot:run


API: http://localhost:8080/api/solicitudes
Front (index): http://localhost:8080/
Swagger UI: http://localhost:8080/swagger




📚 Documentación Swagger

Modelos DTO visibles (con ejemplos)
Validaciones declaradas
Descripciones por endpoint
Si no ves los modelos en Swagger, revisa springdoc-openapi-starter-webmvc-ui en tu pom.xml y que el package base coincida con el de tus controladores/config.



📡 Endpoints

GET — Listar

GET /api/solicitudes

Devuelve JSON en Postman/Swagger.
Devuelve HTML (tabla oscura) en navegador.



POST — Crear (uno o varios)

POST /api/solicitudes
Content-Type: application/json


Ejemplo:

[
{
"titulo": "Pantalla rota",
"descripcion": "No enciende",
"cliente": { "id": 1, "nombre": "Luis", "correo": "luis@example.com" },
"tecnico": { "id": 1, "nombre": "Carlos", "especialidad": "Hardware" },
"estado": "Pendiente",
"fecha": "2025-11-10",
"hora": "14:20"
}
]


PUT — Actualizar por ID

PUT /api/solicitudes/{id}

Revalida todos los campos.
Permite cambiar fecha (YYYY-MM-DD) y hora (HH:mm).



DELETE — Eliminar por ID

DELETE /api/solicitudes/{id}


✅ Validaciones

[TÍTULO]: #1 → El campo 'titulo' es obligatorio
[CLIENTE]: Debe indicar los datos del cliente
[CORREO CLIENTE]: El correo no tiene un formato válido
[FECHA]: La fecha debe tener el formato YYYY-MM-DD
[HORA]: La hora debe tener el formato HH:mm (00–23:59)




Reglas:

titulo, cliente.nombre, tecnico.nombre, tecnico.especialidad → solo letras y espacios
descripcion obligatoria
cliente.correo → formato email
fecha → YYYY-MM-DD
hora → HH:mm (24h)
estado → Pendiente|En proceso|Finalizado|Cancelado


🎨 Vistas HTML

templates/index.html → landing con links a Swagger y tabla.
templates/solicitud.html → formulario que separa datetime-local en fecha + hora, y llama al POST.


🗂 Estructura del proyecto

src/
└─ main/
├─ java/com/empresa/soporte/...
└─ resources/
├─ application.properties
└─ templates/
├─ index.html
└─ solicitud.html



🌿 Estrategia de ramas (Git)

Usamos un Gitflow simplificado pensado para trabajo académico + práctica profesional.

Ramas principales

main → línea estable; solo código liberado y probado.
develop → integración de features; estable a nivel de equipo.

Ramas de soporte

feature/<nombre-corto> → nuevas funcionalidades.
Ej: feature/validacion-fecha-hora

fix/<nombre-corto> → correcciones pequeñas en develop.
Ej: fix/correo-regex

hotfix/<nombre-corto> → corrección urgente desde main.
Ej: hotfix/swagger-ruta

release/<version> → preparar una entrega desde develop.
Ej: release/1.0.0



Comandos típicos

# Crear rama desde develop
git checkout develop
git pull
git checkout -b feature/validacion-fecha-hora

# Trabajar y commitear
git add .
git commit -m "feat(validacion): valida fecha y hora en PUT y POST"

# Subir y abrir PR contra develop
git push -u origin feature/validacion-fecha-hora
# (Abrir Pull Request: feature → develop)

# Crear un release (cuando develop está estable)
git checkout develop
git pull
git checkout -b release/1.0.0
git push -u origin release/1.0.0
# (PR: release → main y release → develop)

# Hotfix desde main
git checkout main
git pull
git checkout -b hotfix/swagger-ruta
# ... fix ...
git push -u origin hotfix/swagger-ruta
# (PR: hotfix → main y hotfix → develop)



Reglas rápidas

Nunca commits directos a main.
PRs con revisión mínima de 1 compañero (si aplica).
Resolver conflictos en la rama del PR, no en main.



✍️ Convenciones de commits

Estilo Conventional Commits:

feat: ... nueva funcionalidad
fix: ... corrección de bug
docs: ... documentación
style: ... formato/código (sin lógica)
refactor: ... refactor interno
test: ... pruebas
chore: ... tareas misceláneas


feat(controller): agrega validación de hora HH:mm
fix(dto): corrige regex de correo
docs(readme): agrega estrategia de ramas


🏷️ Etiquetas y versionado

SemVer: MAJOR.MINOR.PATCH

1.0.0 → primera versión pública estable
1.1.0 → nuevas features sin romper compatibilidad
1.1.1 → hotfix/patch

Tag de release

git checkout main
git pull
git tag -a v1.0.0 -m "Release 1.0.0"
git push origin v1.0.0



🤝 Cómo contribuir
Haz Fork del repo.
Crea rama desde develop:

git checkout develop
git pull
git checkout -b feature/mi-mejora


Cambios + commits con convención.
git push -u origin feature/mi-mejora
Abre PR contra develop (con descripción clara y evidencias).


📜 Licencia

MIT — Uso, modificación y distribución permitidos con atribución.