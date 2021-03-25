package com.salesianostriana.dam.PuzzlesJavi.entities

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
class Pedido(
    @get:NotNull(message="{pedido.fechaPedido.null}")
    var fechaPedido: LocalDate = LocalDate.now(),
    @get:NotNull(message="{pedido.total.null}")
    @get:Min(0, message = "{pedido.total.min}")
    var total: Long,

    @ManyToOne
    var usuario: Usuario? = null,

    @OneToMany(mappedBy = "pedido", fetch=FetchType.EAGER)
    var pedido: MutableList<LineaDePedido> = mutableListOf(),


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