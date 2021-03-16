package entities

import java.time.LocalDate
import javax.persistence.*

@Entity
class Pedido(

    var fechaPedido: LocalDate = LocalDate.now(),
    var total: Long,
    var cantidad: Long,

    @ManyToOne
    var usuario: Usuario? = null,

    @ManyToOne
    var listaDePedido: LineaDePedido? = null,

    @OneToMany(mappedBy = "puzzle")
    var listaPuzzles: MutableList<Puzzle> = mutableListOf(),


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