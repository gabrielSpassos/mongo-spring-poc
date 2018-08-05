package br.gabrielspassos.poc.mongopoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.gabrielspassos.poc.mongopoc"))
                .paths(any())
                .build()
                .apiInfo(infoApi().build());
    }

    private ApiInfoBuilder infoApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Cards-Service");
        apiInfoBuilder.description("Api to study mongoDb with Spring Boot.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.license("Licence - Open Source");
        apiInfoBuilder.contact(contact());

        return apiInfoBuilder;
    }

    private Contact contact() {
        return new Contact(
                "Gabriel Santos dos Passos",
                "https://blogcoreengineering.wordpress.com/",
                "gabrielsantos45725@gmail.com");
    }

}
