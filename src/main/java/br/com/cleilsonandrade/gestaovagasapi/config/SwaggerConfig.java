package br.com.cleilsonandrade.gestaovagasapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Gestão de Vagas - API")
            .description("API for vacancy management")
            .version("v1")
            .license(new License().name("License")
                .url("https://raw.githubusercontent.com/CleilsonAndrade/gestao-vagas-api/main/LICENSE"))
            .contact(new Contact().email("cleilsonjose@hotmail.com").name("Cleilson Andrade")
                .url("https://www.linkedin.com/in/cleilson-andrade/")))
        .schemaRequirement("security", createSecurityScheme());
  }

  private SecurityScheme createSecurityScheme() {
    return new SecurityScheme()
        .name("security")
        .description("Enter a 'Bearer' token to proceed")
        .type(SecurityScheme.Type.HTTP)
        .scheme("Bearer")
        .bearerFormat("JWT");
  }
}
