package org.example.ejercicio08reservaciones.features.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservacionDTO(
        @Schema(example = "15", description = "Id de la reservación")
        Long idReservacion,
        @Schema(example = "1", description = "Id del usuario")
        Long idUsuario,
        @Schema(example = "3", description = "Id del cuarto")
        Long idCuarto,
        @Schema(example = "2026-07-01", description = "Fecha de entrada")
        LocalDate fechaEntrada,
        @Schema(example = "2026-07-05", description = "Fecha de salida")
        LocalDate fechaSalida,
        @Schema(example = "ACTIVA", description = "Estado actual de la reservación")
        String estado,
        @Schema(example = "2026-06-24T12:30:45", description = "Fecha y hora de creación")
        LocalDateTime fechaCreacion
) {
}
