package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return (new OpenAPI()).info((new Info()).title("API - Sistema de Gestión de Solicitudes").description("Proyecto de Examen Parcial").version("1.0.0").contact((new Contact()).name("IDAT").email("a1512235@idat.edu.pe").url("https://idat.edu.pe")));
    }
}
