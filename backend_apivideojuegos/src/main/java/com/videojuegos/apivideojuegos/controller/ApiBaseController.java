
package com.videojuegos.apivideojuegos.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition(
    info = @Info(
        title = "Api Tienda de videojuegos",
        version = "1.0",
        description = "Api para gerenciamento de videojuegos y ventas de una tienda de videjuegos"
    ),
    tags = {
        @Tag(
            name = "Base Controller",
            description = "Controlador base para la api, este controlador se extendera a todos los endpoints del api"
        )
    }
)
public class ApiBaseController {
    
}
