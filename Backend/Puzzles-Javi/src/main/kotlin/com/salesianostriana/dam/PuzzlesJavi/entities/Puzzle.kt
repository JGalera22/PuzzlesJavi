package com.salesianostriana.dam.PuzzlesJavi.entities

import javax.persistence.*

@Entity
class Puzzle(

    @Column(unique = true) var nombre : String,
    var descripcion: String,
    var precio: Long,
    var numeroPiezas: Long,
    var categoria: String,

    @ManyToOne
    var admin: Usuario? = null,


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