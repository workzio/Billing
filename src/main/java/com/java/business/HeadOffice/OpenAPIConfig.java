package com.java.business.HeadOffice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in the dev environment.");

        Server prodServer = new Server();
        prodServer.setUrl("https://tamilskillhub.com");
        prodServer.setDescription("Server URL in the prod environment.");

        Contact contact = new Contact();
        contact.setEmail("tamilskillhub@gmail.com");
        contact.setName("Tamil Skillhub");
        contact.setUrl("https://tamilskillhub.com");

        License mitLicense = new License().name("MIT License")
                                          .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://tamilskillhub.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer));
    }
}
