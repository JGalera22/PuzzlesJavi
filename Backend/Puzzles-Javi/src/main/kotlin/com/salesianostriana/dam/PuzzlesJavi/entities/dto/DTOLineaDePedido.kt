package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import com.salesianostriana.dam.PuzzlesJavi.entities.LineaDePedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario

data class GetLineaDePedidoDto (
    var id: Long?,
    var precio: Long,
    var cantidad: Long,
        )

fun LineaDePedido.toGetLineaDePedidoDto(usuario: Usuario?):GetLineaDePedidoDto{
    return GetLineaDePedidoDto(id, precio, cantidad)
}


data class getLineaPedidoDetalleDto(
        var precio: Long,
        var cantidad: Long,
        var puzzle: GetPuzzleDto,
)



