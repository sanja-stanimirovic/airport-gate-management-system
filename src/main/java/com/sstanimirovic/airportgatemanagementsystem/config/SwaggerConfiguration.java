package com.sstanimirovic.airportgatemanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sstanimirovic.airportgatemanagementsystem.controller"))
                .paths(any())
                .build()
                .apiInfo(new ApiInfo(
                        "Airport Gate Management System API",
                        "Airport Gate Management System API for developers",
                        "1.0.0",
                        "#", new Contact("Sanja Stanimirovic", "#", "@"),
                        null,
                        "#",
                        Collections.emptyList()
                ));
    }
}
