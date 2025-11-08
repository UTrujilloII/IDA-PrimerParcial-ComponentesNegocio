***************************************
Integrantes:
- Kaito Zaid Chota Shuña
- Josmel Montoya Paiva
- Guillermo Arturo Ugaz Montesinos
- Alexander jose illescas flores
- Cris Angelo Rivera Cardenas
***************************************


Informe Tecnico:
Se desarrolló una API RESTful utilizando Java y Spring Boot, que permite registrar, consultar, actualizar y eliminar solicitudes de soporte técnico, garantizando una gestión digital, ordenada y centralizada de la información.


Software usado:
Java JDK 21 o 22
IntelliJ IDEA 2025.2.2
Apache Maven 3.9.6

  
Dependencias:
Spring Web
Rest Repositories
Spring Boot Dev Tools
Projectlombok
springdoc openapi


Instalación:
Para ejecutar primero necesitas instalar Java JDK (version 20 o superior), Apache Maven 3.9 y un Ide de preferencia, luego basta con descargar el proyecto y descomprimirlo.


Documentación de API disponible en:
http://localhost:8080/swagger-ui/index.html


Pruebas del Sistema
201 Created → Registro exitoso
200 OK → Consulta o actualización correcta
204 No Content → Eliminación exitosa
404 Not Found → Registro no existente


Endpoints de la API:
Método	          URL	                 Descripción
POST	    /api/solicitudes	         Crear nueva solicitud
GET	        /api/solicitudes	 Listar todas las solicitudes
GET	        /api/solicitudes/{id}	 Buscar solicitud por ID
PUT	        /api/solicitudes/{id}	 Actualizar solicitud por ID
DELETE	    /api/solicitudes/{id}	 Eliminar solicitud por ID

POST	    /api/clientes	         Crear nuevo cliente
GET	        /api/clientes	         Lista todos los clientes
GET	        /api/clientes/{id}	 Buscar cliente por ID
PUT	        /api/clientes/{id}	 Actualizar cliente con ID
DELETE	    /api/clientes/{id}	         Eliminar cliente con ID

POST	    /api/tecnicos	         Crear nuevo cliente
GET	        /api/tecnicos	         Lista todos los clientes
GET	        /api/tecnicos/{id}	 Buscar cliente por ID
PUT	        /api/tecnicos/{id}	 Actualizar cliente con ID
DELETE	    /api/tecnicos/{id}	         Eliminar cliente con ID
