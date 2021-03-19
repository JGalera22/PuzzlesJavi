package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import java.time.LocalDate

data class GetPedidoDto (
    var id: Long,
    var fechaPedido: LocalDate = LocalDate.now(),
    var total: Long,
)



