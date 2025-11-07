package com.empresa.soporte.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ConfiguraciOn del Swagger para generar la documentación del API.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI soporteApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("📘 API de Soporte Técnico")
                        .description("""
                                Sistema CRUD de solicitudes de soporte técnico.
                                Desarrollado por **Grupo Cuatro** — IDAT 2025.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Grupo 4")
                                .email("Grup4.soporte@empresa.com")
                                .url("https://www.empresa.com/soporte"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
