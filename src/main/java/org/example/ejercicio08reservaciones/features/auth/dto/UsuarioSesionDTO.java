package org.example.ejercicio08reservaciones.features.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record UsuarioSesionDTO(
        @Schema(example = "1", description = "Id del usuario autenticado")
        Long idUsuario,
        @Schema(example = "cristian", description = "Nombre de usuario")
        String username,
        @Schema(example = "cristian@email.com", description = "Correo del usuario")
        String email,
        @Schema(example = "true", description = "Indica si la cuenta está habilitada")
        Boolean habilitado,
        @Schema(example = "false", description = "Indica si la cuenta está bloqueada")
        Boolean bloqueado,
        @Schema(example = "ADMIN,USER", description = "Lista de roles asignados")
        List<String> roles
) {
}
