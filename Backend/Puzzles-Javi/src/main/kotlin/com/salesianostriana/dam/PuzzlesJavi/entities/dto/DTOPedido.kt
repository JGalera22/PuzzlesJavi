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


data class GetPedidoDetalleDto(
    var id: Long?,
    var fechaPedido: LocalDate = LocalDate.now(),
    var total: Long,
    var lineaPedido: MutableList<GetLineaDePedidoDto>,
)

fun Pedido.toGetPedidoDetalleDto(usuario: Usuario?):GetPedidoDetalleDto{
    var pedido: MutableList<GetLineaDePedidoDto> = mutableListOf()
    val lineaDePedido: List<GetLineaDePedidoDto> = listOf()
    lineaDePedido!!.forEach { i ->
        pedido.add(GetLineaDePedidoDto(i.id, i.precio, i.cantidad))
    }
    return GetPedidoDetalleDto(id, fechaPedido, total, pedido)
}
