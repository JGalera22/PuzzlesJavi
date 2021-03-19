package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import java.time.LocalDate

data class getLineaDePedidoDto (
    var fechaPedido: LocalDate,
    var cantidad: Long,
        )
data class getLineaPedidoDetalleDto(
        var fechaPedido: LocalDate,
        var cantidad: Long,
        var puzzle: GetPuzzleDto,
)
