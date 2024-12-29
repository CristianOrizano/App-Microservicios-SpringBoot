package com.auth.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemaName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Auth")
                        .version("v1")
                        .description("Microservicio de autenticacion")
                        .contact(new Contact()
                                .name("Cristian Orizano")));
    }
}
