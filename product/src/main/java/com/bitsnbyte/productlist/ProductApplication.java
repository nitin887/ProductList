package com.bitsnbyte.productlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@OpenAPIDefinition(info = @Info(title = "Product SERVICE REST API", version = "V1", description = "API for managing products", contact = @Contact(name = "Bitsnbyte", email = "bitsnbyte@gmail.com")), externalDocs = @ExternalDocumentation(description = "Product Service Documentation", url = "https://github.com/bitsnbyte/product-service"))
@SpringBootApplication
@Slf4j
public class ProductApplication {
	// private static final Logger log =
	// LoggerFactory.getLogger(ProductApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProductApplication.class);
		// If no profile is active, default to 'local' so developers can run the app without MySQL
		String active = System.getProperty("spring.profiles.active");
		if (active == null || active.isBlank()) {
			app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "local"));
		}
		app.run(args);
		String str = "test";
		log.info("product service started successfully!{}", str);
		// log.warn("this is a warning message");
		// log.debug("this is a debug message");
		// log.error("this is an error message");
		// log.trace("this is a trace message");
	}

}
