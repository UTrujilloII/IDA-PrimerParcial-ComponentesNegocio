package com.empresa.soporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SoporteApiApplication {

    public static void main(String[] args) {
        // Inicia la aplicación y obtiene el contexto
        ConfigurableApplicationContext context = SpringApplication.run(SoporteApiApplication.class, args);
        Environment env = context.getEnvironment();

        // Obtiene el puerto configurado o el predeterminad
        String port = env.getProperty("server.port", "8080");
        String url = "http://localhost:" + port;

        System.out.println("\n🚀 Servidor iniciado con éxito!");
        System.out.println("🌐 URL de inicio: " + url);
        System.out.println("📡 API disponible en: " + url + "/api/solicitudes\n");
    }
}
