package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import java.time.LocalDate

data class GetPedidoDto (
    var id: Long?,
    var fechaPedido: LocalDate = LocalDate.now(),
    /*var total: Long,*/
)

fun Pedido.toGetPedidoDto(usuario: Usuario?):GetPedidoDto{
    return GetPedidoDto(id, fechaPedido)
}

data class GetPedidoDetalleDto(
    var id: Long?,
    var fechaPedido: LocalDate = LocalDate.now(),
    /*var total: Long,*/
)

//fun Pedido.toGetPedidoDetalleDto(usuario: Usuario?):GetPedidoDetalleDto{
//    var pedido: MutableList<GetLineaPedidoDto> = mutableListOf()
//    return GetPedidoDetalleDto(id, fechaPedido)
//}

//data class GetLineaPedidoDto(
//    var id: Long?,
//    var lineaPedido: MutableList<Puzzle>,
//)
//
//
//fun Pedido.toGetLineaPedidoDto(usuario: Usuario?): GetLineaPedidoDto {
//    return GetLineaPedidoDto(id, lineaPedido)
//}

