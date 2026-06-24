package org.example.ejercicio08reservaciones.features.room.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ejercicio08reservaciones.features.room.dto.CreateCuartoDTO;
import org.example.ejercicio08reservaciones.features.room.dto.CuartoDTO;
import org.example.ejercicio08reservaciones.features.room.dto.UpdateCuartoDTO;
import org.example.ejercicio08reservaciones.features.room.dto.UpdateDisponibilidad;
import org.example.ejercicio08reservaciones.features.room.service.CuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuartos")
@RequiredArgsConstructor
@Tag(name = "Cuarto", description = "Operaciones para las habitaciones")
public class CuartoController {
    private final CuartoService cuartoService;

    @PostMapping
    @Operation(summary = "Crear cuarto", description = "Registra un nuevo cuarto en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cuarto creado correctamente",
            content = @Content(schema = @Schema(implementation = CuartoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<CuartoDTO> createCuarto(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos necesarios para crear un cuarto",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateCuartoDTO.class)))
        @Valid @RequestBody CreateCuartoDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuartoService.createCuarto(dto));
    }

    @GetMapping
    @Operation(summary = "Listar cuartos", description = "Obtiene todos los cuartos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cuartos",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CuartoDTO.class)))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<List<CuartoDTO>> findAllCuartos() {
        return ResponseEntity.ok(cuartoService.readAllCuartos());
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Listar cuartos disponibles", description = "Obtiene solo los cuartos marcados como disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cuartos disponibles",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CuartoDTO.class)))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<List<CuartoDTO>> findCuartosDisponibles() {
        return ResponseEntity.ok(cuartoService.readCuartosDisponibles());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar cuarto por id", description = "Obtiene la información de un cuarto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuarto encontrado",
            content = @Content(schema = @Schema(implementation = CuartoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuarto no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<CuartoDTO> findCuarto(
        @Parameter(description = "Id del cuarto", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(cuartoService.readById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cuarto", description = "Actualiza la información general de un cuarto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuarto actualizado",
            content = @Content(schema = @Schema(implementation = CuartoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Cuarto no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<CuartoDTO> updateCuarto(
        @Parameter(description = "Id del cuarto", example = "1") @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para actualizar el cuarto",
            required = true,
            content = @Content(schema = @Schema(implementation = UpdateCuartoDTO.class)))
        @Valid @RequestBody UpdateCuartoDTO dto) {
        return ResponseEntity.ok(cuartoService.updateCuarto(id, dto));
    }

    @PatchMapping("/{id}/disponibilidad")
    @Operation(summary = "Actualizar disponibilidad", description = "Cambia el estado de disponibilidad de un cuarto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Disponibilidad actualizada",
            content = @Content(schema = @Schema(implementation = CuartoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Cuarto no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<CuartoDTO> updateCuartoDisponibilidad(
        @Parameter(description = "Id del cuarto", example = "1") @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Valor de disponibilidad del cuarto",
            required = true,
            content = @Content(schema = @Schema(implementation = UpdateDisponibilidad.class)))
        @Valid @RequestBody UpdateDisponibilidad dto) {
        return  ResponseEntity.ok(cuartoService.updateDisponibilidad(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuarto", description = "Elimina un cuarto por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cuarto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Cuarto no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<Void> deleteCuarto(@PathVariable Long id) {
        cuartoService.deleteCuarto(id);
        return ResponseEntity.noContent().build();
    }

}
