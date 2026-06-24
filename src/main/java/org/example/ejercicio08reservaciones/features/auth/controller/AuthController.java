package org.example.ejercicio08reservaciones.features.auth.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.ejercicio08reservaciones.features.auth.dto.UsuarioSesionDTO;
import org.example.ejercicio08reservaciones.features.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Operaciones de autenticación y sesión")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/me")
    @Operation(summary = "Usuario autenticado", description = "Retorna la información del usuario autenticado actualmente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información del usuario autenticado",
                    content = @Content(schema = @Schema(implementation = UsuarioSesionDTO.class))),
            @ApiResponse(responseCode = "401", description = "No autenticado")
    })
    public ResponseEntity<UsuarioSesionDTO> me(@Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity.ok(authService.obtenerUsuarioAutenticado(authentication.getName()));
    }
}
