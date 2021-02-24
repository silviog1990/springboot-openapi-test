package dev.silvio.springboot.openapi;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Open API Spring Boot with Tests",
        version = "${apiversion}",
        description = "description",
        contact = @Contact(name = "", url = "", email = ""),
        license = @License(name = "", url = "")),
        servers = @Server(url = ""))
@Configuration
public class OpenAPIConfig {
}