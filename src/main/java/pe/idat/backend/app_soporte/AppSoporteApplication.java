package pe.idat.backend.app_soporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSoporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSoporteApplication.class, args);
		System.out.println("========================================");
		System.out.println("API de Soporte Técnico iniciada");
		System.out.println("Accede a: http://localhost:8080");
		System.out.println("========================================");
	}
}