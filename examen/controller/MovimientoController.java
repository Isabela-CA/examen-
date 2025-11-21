package com.logitrack.controller;

import com.logitrack.dto.MovimientoRequest;
import com.logitrack.dto.MovimientoResponse;
import com.logitrack.model.Movimiento;
import com.logitrack.service.MovimientoService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.logitrack.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
@Tag(name = "Movimientos", description = "Gestión de Movimientos de Inventario (Entrada/Salida/Transferencia)")
public class MovimientoController {

    public final MovimientoService service;

    @GetMapping("/movimientos/recientes")
    @Operation(summary = "Buscar movimientos con los ultimos 10 registros - fecha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Lista de movimientos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovimientoResponse.class)))
    })
    public ResponseEntity<List<MovimientoResponse>> getByFechas(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
    return ResponseEntity.ok(service.findByFechas(inicio, fin));
}

}