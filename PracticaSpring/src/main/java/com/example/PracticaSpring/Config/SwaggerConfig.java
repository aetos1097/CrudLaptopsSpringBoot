package com.example.PracticaSpring.Config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {

    @Bean
    public OpenAPI springLaptopsAPI(){
        return new OpenAPI().info(new Info().title("Practica con Swagger :3")
                .description("Api de laptops con metodo Crd")
                .version("1.0")
                .license(new License().name("License").url("http://www.apache.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("documentation").
                        url("http://localhost:8080/"));


    }
}
