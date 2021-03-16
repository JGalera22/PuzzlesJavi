package com.salesianostriana.dam.G1E1RealEstate.services


import com.salesianostriana.dam.G1E1RealEstate.services.base.BaseServiceImpl
import entities.Usuario
import org.springframework.stereotype.Service
import repositories.UsuarioRepository
import java.time.LocalDate
import java.util.*

@Service
class UsuarioService() : BaseServiceImpl<Usuario, Long, UsuarioRepository>() {
    fun findByUsername(username: String) = repositorio?.findByUsername(username)

}