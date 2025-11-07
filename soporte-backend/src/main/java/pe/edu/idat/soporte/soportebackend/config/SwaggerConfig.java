package pe.edu.idat.soporte.soportebackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API DE SOLICITUDES")
                        .version("1.0.0")
                        .description("API para gestionar solicitudes de soporte técnico: crear, consultar, actualizar y eliminar solicitudes.")
                        .contact(new Contact()
                                .name("Repositorio")
                                .url("https://github.com/joel-machaca/soporte-backend-ciclo4")
                        )
                )
                .addServersItem(new Server()
                        .description("Servidor de desarrollo")
                        .url("http://localhost:8080")
                );
    }
}
