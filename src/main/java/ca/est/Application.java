package ca.est;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Estevam Meneses
 */
@ComponentScan("ca.est")
@EntityScan("ca.est.entity")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("*************************************************************");
		System.out.println("Swagger  UI -> http://localhost:8080/app/swagger-ui/index.html");
		System.out.println("Database H2 -> http://localhost:8080/app/h2");
		System.out.println("*************************************************************");
	}
}
