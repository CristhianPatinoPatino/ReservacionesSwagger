package org.example.ejercicio08reservaciones.features.reservation.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ejercicio08reservaciones.features.reservation.dto.CreateReservacionDTO;
import org.example.ejercicio08reservaciones.features.reservation.dto.ReservacionDTO;
import org.example.ejercicio08reservaciones.features.reservation.dto.UpdateReservacionDTO;
import org.example.ejercicio08reservaciones.features.reservation.service.ReservacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservaciones")
@RequiredArgsConstructor
@Tag(name = "Reservacion", description = "Operaciones para la gestión de reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;

    @PostMapping
    @Operation(summary = "Crear reservación", description = "Registra una nueva reservación para un usuario y un cuarto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reservación creada correctamente",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<ReservacionDTO> createReservacion(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para crear la reservación",
            required = true,
            content = @Content(schema = @Schema(implementation = CreateReservacionDTO.class)))
        @Valid @RequestBody CreateReservacionDTO dto,
        @Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservacionService.createReservacion(dto, authentication.getName()));
    }

    @GetMapping
    @Operation(summary = "Listar reservaciones", description = "Obtiene todas las reservaciones del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservaciones",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<List<ReservacionDTO>> findAllReservaciones() {
        return ResponseEntity.ok(reservacionService.readAllReservaciones());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar reservación por id", description = "Obtiene una reservación específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación encontrada",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "404", description = "Reservación no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<ReservacionDTO> findReservacion(
        @Parameter(description = "Id de la reservación", example = "1") @PathVariable Long id,
        @Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity.ok(reservacionService.readById(id, authentication.getName()));
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Listar reservaciones por usuario", description = "Obtiene las reservaciones asociadas a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservaciones del usuario",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<List<ReservacionDTO>> findReservacionesByUsuario(
        @Parameter(description = "Id del usuario", example = "1") @PathVariable Long idUsuario,
        @Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity.ok(reservacionService.readByUsuario(idUsuario, authentication.getName()));
    }

    @GetMapping("/cuarto/{idCuarto}")
    @Operation(summary = "Listar reservaciones por cuarto", description = "Obtiene las reservaciones asociadas a un cuarto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservaciones del cuarto",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<List<ReservacionDTO>> findReservacionesByCuarto(
        @Parameter(description = "Id del cuarto", example = "1") @PathVariable Long idCuarto) {
        return ResponseEntity.ok(reservacionService.readByCuarto(idCuarto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reservación", description = "Actualiza las fechas de una reservación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación actualizada",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Reservación no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<ReservacionDTO> updateReservacion(
        @Parameter(description = "Id de la reservación", example = "1") @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para actualizar la reservación",
            required = true,
            content = @Content(schema = @Schema(implementation = UpdateReservacionDTO.class)))
        @Valid @RequestBody UpdateReservacionDTO dto,
        @Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity.ok(reservacionService.updateReservacion(id, dto, authentication.getName()));
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar reservación", description = "Cambia el estado de una reservación a cancelada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservación cancelada",
            content = @Content(schema = @Schema(implementation = ReservacionDTO.class))),
        @ApiResponse(responseCode = "404", description = "Reservación no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<ReservacionDTO> cancelarReservacion(
        @Parameter(description = "Id de la reservación", example = "1") @PathVariable Long id,
        @Parameter(hidden = true) Authentication authentication) {
        return ResponseEntity.ok(reservacionService.cancelarReservacion(id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reservación", description = "Elimina una reservación del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reservación eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reservación no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<Void> deleteReservacion(@PathVariable Long id) {
        reservacionService.deleteReservacion(id);
        return ResponseEntity.noContent().build();
    }
}
