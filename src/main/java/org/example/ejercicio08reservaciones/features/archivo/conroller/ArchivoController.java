package org.example.ejercicio08reservaciones.features.archivo.conroller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.ejercicio08reservaciones.core.domain.Archivo;
import org.example.ejercicio08reservaciones.features.archivo.dto.RespuestaDTO;
import org.example.ejercicio08reservaciones.features.archivo.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/apiArchivos/v1/archivo")
@Tag(name = "Archivo", description = "Operaciones para subir y descargar archivos")
public class ArchivoController {
    @Autowired
    private ArchivoService service;

    @PostMapping(value = "/subirArchivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir archivo", description = "Carga un archivo y lo guarda en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Archivo subido correctamente",
            content = @Content(schema = @Schema(implementation = RespuestaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Archivo inválido"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<RespuestaDTO> subirArchivo(
        @Parameter(description = "Archivo a subir", required = true)
        @RequestParam MultipartFile archivo) throws IOException {
        service.guardarArchivoEnBaseDeDatos(archivo);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setMensaje("Archivo subido correctamente");
        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping("/descargarArchivo/{id}")
    @Operation(summary = "Descargar archivo", description = "Descarga el archivo almacenado por su id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Archivo descargado correctamente",
            content = @Content(mediaType = "application/octet-stream",
                schema = @Schema(type = "string", format = "binary"))),
        @ApiResponse(responseCode = "404", description = "Archivo no encontrado"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    public ResponseEntity<byte[]> descargarArchivo(@Parameter(description = "Id del archivo", example = "1") @PathVariable Long id) throws FileNotFoundException {
        Archivo archivo = service.descargarArchivo(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, archivo.getTipoArchivo())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attacgment; filename=\""
                        + archivo.getNombreArchivo() + "\""
                ).body(archivo.getDatosArchivo());
    }
}
