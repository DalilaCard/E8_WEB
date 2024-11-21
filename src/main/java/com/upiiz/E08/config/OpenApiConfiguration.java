package com.upiiz.E08.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
// Personalizar la documentación de nuestra api
@OpenAPIDefinition(
        info = @Info(
                title = "API de Deportes",
                description = "Esta API proporciona acceso a los recursos de la API de Deportes",
                version = "1.0.0",
                contact = @Contact(
                        name = "Martha Dalila Cardona Serna",
                        email = "mcardonas2000@alumno.ipn.mx",
                        url = "http://localhost:8081/contacto"
                ),
                license = @License(),
                termsOfService = "Solo permiten 400 solicitudes diarias"
        ),
        servers = {
                @Server(
                        description = "Servidor de pruebas",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Servidor de Produccion",
                         url = "http://localhost:8080/api/v1/equipo"
                )
        },
        tags = {
                @Tag(
                        name = "Equipo",
                        description = "Endpoints para los recursos del equipo"
                ),
                @Tag(
                        name = "Jugadores",
                        description = "Endpoints para los recursos de jugadores"
                ),
                @Tag(
                        name = "Entrenadores",
                        description = "Endpoints para los recursos del entrenador"
                ),
                @Tag(
                        name = "Liga",
                        description = "Endpoints para los recursos de la liga"
                ),
                @Tag(
                        name = "Competencias",
                        description = "Endpoints para los recursos de las competencia"
                )
        }
)
public class OpenApiConfiguration {
}
