package com.tienda.appclient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger para documentación automática de la API.
 * Proporciona información detallada sobre endpoints, modelos y operaciones disponibles.
 */
@Configuration
public class Config {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Solicitudes de Soporte Técnico")
                        .version("1.0.0")
                        .description("API RESTful para gestionar solicitudes de soporte técnico, clientes y técnicos. " +
                                "Permite operaciones CRUD completas con validaciones y manejo de excepciones centralizado.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Backend")
                                .email("soporte@tienda.com")));
    }
}

