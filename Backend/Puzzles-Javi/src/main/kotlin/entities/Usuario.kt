package entities

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
class Usuario(

    @get:NotBlank(message = "{usuario.username.blank}")
    @get:Size( message = "{usuario.username.size}", min= 4, max= 20)
    @Column(unique = true)
    var username : String,

    @get:Size( message = "{usuario.password.size}", min= 8, max = 100)
    var passwd: String,

    @get:Email( message = "{usuario.email.email}")
    var email: String,

    @get:NotBlank(message = "{usuario.nombreCompleto.blank}")
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