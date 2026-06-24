package org.example.ejercicio08reservaciones.features.room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateDisponibilidad(
        @Schema(example = "true", description = "Indica si el cuarto está disponible o no")
        @NotNull(message =
                "El status para la disponibilidad es obligatorio (está o no disponible(falso o verdadero))")
        Boolean disponible
) {
}
