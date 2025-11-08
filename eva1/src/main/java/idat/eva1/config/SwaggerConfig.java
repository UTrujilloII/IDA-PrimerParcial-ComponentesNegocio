package idat.eva1.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI soporteApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Aplicativo Solucion")
                        .description("""
                                Sistema Api back-end : Grupo 5
                                - Kaito Zaid Chota Shuña
                                - Josmel Montoya Paiva
                                - Guillermo Arturo Ugaz Montesinos
                                - Alexander Jose Illescas Flores
                                - Cris Angelo Rivera Cardenas
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Grupo 5")
                                .email("Grup5.ejemplo@idat.com")
                                .url("http://localhost:8080/swagger-ui/index.html#/"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
