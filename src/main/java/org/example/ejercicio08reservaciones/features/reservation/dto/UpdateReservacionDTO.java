package org.example.ejercicio08reservaciones.features.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateReservacionDTO(
        @Schema(example = "2026-07-01", description = "Nueva fecha de entrada")
        @NotNull(message = "La fecha de entrada es obligatoria")
        @FutureOrPresent(message = "La fecha de entrada debe ser presente o futura")
        LocalDate fechaEntrada,

        @Schema(example = "2026-07-05", description = "Nueva fecha de salida")
        @NotNull(message = "La fecha de salida es obligatoria")
        LocalDate fechaSalida
) {
}
