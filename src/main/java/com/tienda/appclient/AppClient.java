package com.tienda.appclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Punto de entrada del sistema de gestión de solicitudes de soporte técnico.
 *
 * Esta aplicación proporciona una API REST completa para:
 * - Gestión de clientes
 * - Gestión de técnicos
 * - Gestión de solicitudes de soporte
 *
 * @author Equipo de Desarrollo Backend
 * @version 1.0.0
 */
@SpringBootApplication
public class AppClient {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(AppClient.class, args);
    }
}

