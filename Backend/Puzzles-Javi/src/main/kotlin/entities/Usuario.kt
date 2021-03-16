package entities

import java.time.LocalDate
import javax.persistence.*


@Entity
class Usuario(

    @Column(unique = true) var username : String,
    var passwd: String,
    var email: String,
    var nombreCompleto: String,
    var fechaAlta: LocalDate = LocalDate.now(),

    var vip : Boolean = false,
    var activo: Boolean = true,


    @ManyToMany
    @JoinTable(name = "Lista_deseados",
        joinColumns = [JoinColumn(name="usuario_id")],
        inverseJoinColumns = [JoinColumn(name="puzzle_id")]
    )
    var puzzlesDeseados: MutableList<Puzzle> = mutableListOf(),


    @OneToMany(mappedBy = "pedido")
    var listaPedido: MutableList<Pedido> = mutableListOf(),

    @OneToMany(mappedBy = "historial")
    var historial: MutableList<LineaDePedido> = mutableListOf(),


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