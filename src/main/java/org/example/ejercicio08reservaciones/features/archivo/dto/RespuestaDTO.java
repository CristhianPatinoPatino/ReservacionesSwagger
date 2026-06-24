package org.example.ejercicio08reservaciones.features.archivo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Respuesta genérica para operaciones de archivos")
@Data
public class RespuestaDTO {
    @Schema(example = "Archivo subido correctamente", description = "Mensaje informativo de la operación")
    private String mensaje;
}
