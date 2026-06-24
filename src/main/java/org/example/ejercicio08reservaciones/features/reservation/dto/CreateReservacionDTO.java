package org.example.ejercicio08reservaciones.features.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateReservacionDTO(
        @Schema(example = "1", description = "Id del usuario que realiza la reservación")
        @NotNull(message = "El id del usuario es obligatorio")
        @Positive(message = "El id del usuario debe ser mayor a 0")
        Long idUsuario,

        @Schema(example = "3", description = "Id del cuarto reservado")
        @NotNull(message = "El id del cuarto es obligatorio")
        @Positive(message = "El id del cuarto debe ser mayor a 0")
        Long idCuarto,

        @Schema(example = "2026-07-01", description = "Fecha de entrada")
        @NotNull(message = "La fecha de entrada es obligatoria")
        @FutureOrPresent(message = "La fecha de entrada debe ser presente o futura")
        LocalDate fechaEntrada,

        @Schema(example = "2026-07-05", description = "Fecha de salida")
        @NotNull(message = "La fecha de salida es obligatoria")
        LocalDate fechaSalida
) {
}
