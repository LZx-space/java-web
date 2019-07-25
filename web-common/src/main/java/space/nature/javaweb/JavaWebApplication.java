package space.nature.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JavaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaWebApplication.class, args);
	}

}
