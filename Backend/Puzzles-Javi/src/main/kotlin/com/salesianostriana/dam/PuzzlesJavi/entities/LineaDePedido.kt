package com.salesianostriana.dam.PuzzlesJavi.entities

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
class LineaDePedido(
    @get:NotNull(message="{lineaDePedido.precio.null}")
    @get:Min(0, message = "{lineaDePedido.precio.min}")
    var precio: Long,
    @get:NotNull(message="{lineaDePedido.cantidad.null}")
    @get:Min(1, message = "{lineaDePedido.cantidad.min}")
    var cantidad: Long,

    @ManyToOne
    var puzzle: Puzzle? = null,

    @ManyToOne
    var pedido: Pedido? = null,

    @Id @GeneratedValue val id : Long? = null

){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Usuario
        if (id != that.id) return false
        return true
    }


    override fun hashCode(): Int {
        return if (id != null)
            id.hashCode()
        else 0
    }

}