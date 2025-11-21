package com.logitrack.controller;

import com.logitrack.dto.MovimientoResponse;
import com.logitrack.dto.ReporteResumen;
import com.logitrack.service.MovimientoService;
import com.logitrack.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Reportes y consultas avanzadas")
public class ReporteController {

    private final ReporteService reporteService;
    private final MovimientoService movimientoService;

    @GetMapping("/resumen")
    @Operation(summary = "Generar resumen de reportes con umbral opcional")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Resumen generado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReporteResumen.class)))
    })
    public ResponseEntity<ReporteResumen> resumen(@RequestParam(required = false) Integer threshold) {
        ReporteResumen resumen = (threshold == null)
                ? reporteService.generarResumen()
                : reporteService.generarResumen(threshold);
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/movimientos/{tipo}")
    @Operation(summary = "Obtener cantidad por movimientos por tipo (ENTRADA, SALIDA, TRANSFERENCIA)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Lista de movimientos por tipo",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovimientoResponse.class)))
    })
    public ResponseEntity<List<MovimientoResponse>> getByTipo(@PathVariable String tipo) {
        Movimiento.TipoMovimiento t = Movimiento.TipoMovimiento.valueOf(tipo.toUpperCase());
        return ResponseEntity.ok(service.findByTipo(t));
    }
}


