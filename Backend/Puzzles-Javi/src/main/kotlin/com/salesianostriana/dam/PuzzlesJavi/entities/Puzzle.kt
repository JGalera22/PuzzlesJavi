package com.salesianostriana.dam.PuzzlesJavi.entities

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Puzzle(

    @get:NotBlank(message = "{puzzle.nombre.blank}")
    var nombre : String,
    @get:NotBlank(message = "{puzzle.descripcion.blank}")
    var descripcion: String,
    @get:NotNull(message="{puzzle.precio.null}")
    @get:Min(0, message = "{puzzle.precio.min}")
    var precio: Long,
    @get:NotNull(message="{puzzle.numeroPiezas.null}")
    @get:Min(0, message = "{puzzle.numeroPiezas.min}")
    var numeroPiezas: Long,
    @get:NotNull(message="{puzzle.categoria.blank}")
    var categoria: String,

    @ManyToOne
    var usuario: Usuario? = null,


    @ManyToMany
    @JoinTable(name = "Lista_deseados",
        joinColumns = [JoinColumn(name="puzzle_id")],
        inverseJoinColumns = [JoinColumn(name="usuario_id")]
    )
    var usuariosDeseados: MutableList<Usuario> = mutableListOf(),

    @OneToMany(mappedBy="puzzle", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    var imagenes: MutableList<ImagenPuzzle> = mutableListOf(),


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