package com.microservice.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "TheRealCodeShow Accounts microservice API Documentation",
                version = "v1",
                contact = @Contact(
                        name="Mauricio Perez",
                        email="mauriciomaps42@gmail.com",
                        url="https://mauri-portfolio.vercel.app/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://mauri-portfolio.vercel.app/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "TheRealCodeShow Bank Accounts microservice REST API Documentation",
                url = "https://mauri-portfolio.vercel.app/"
        )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
