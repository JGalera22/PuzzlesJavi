package com.salesianostriana.dam.PuzzlesJavi.repositories

import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByUsername(username: String) : Optional<Usuario>
}