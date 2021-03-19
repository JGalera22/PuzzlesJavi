package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import java.time.LocalDate

data class GetPedidoDto (
    var id: Long?,
    var fechaPedido: LocalDate = LocalDate.now(),
    var total: Long,
)

fun Pedido.toGetPedidoDto(usuario: Usuario?):GetPedidoDto{
    return GetPedidoDto(id, fechaPedido, total)
}



