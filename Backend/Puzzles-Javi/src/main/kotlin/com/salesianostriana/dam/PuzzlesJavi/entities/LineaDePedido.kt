package com.salesianostriana.dam.PuzzlesJavi.entities

import javax.persistence.*

@Entity
class LineaDePedido(

    var precio: Long,
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