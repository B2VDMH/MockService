package hu.gdf.thesis.MockService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class MockServiceApplication {

	public static void main(String[] args) {
		if(args.length<1 ||
				!args[0].startsWith("--spring.config.location=")) {
			log.info("Please add the full path to your " +
					"Spring Boot configuration file as argument.");
			System.exit(1);
		}
		SpringApplication.run(MockServiceApplication.class, args);
	}
}
