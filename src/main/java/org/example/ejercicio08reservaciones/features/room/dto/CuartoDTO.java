package org.example.ejercicio08reservaciones.features.room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

/**
 * DTO QUE VA A REPRESENTAR LA INFORMACIÓN QUE SE MUESTRA AL RECUPERAR LA HABITACIÓN
 * @param id
 * @param numero
 * @param precio
 * @param numeroCamas
 * @param disponible
 */

public record CuartoDTO(
        @Schema(example = "1", description = "Identificador del cuarto")
        long id,
        @Schema(example = "Suite Presidencial", description = "Tipo de la habitación")
        String tipo,
        @Schema(example = "101", description = "Número del cuarto")
        int numero,
        @Schema(example = "1250.50", description = "Precio por noche del cuarto")
        BigDecimal precio,
        @Schema(example = "2", description = "Cantidad de camas")
        int numeroCamas,
        @Schema(example = "true", description = "Disponibilidad actual del cuarto")
        Boolean disponible
) {
}
