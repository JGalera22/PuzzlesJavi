package com.salesianostriana.dam.G1E1RealEstate.services


import com.salesianostriana.dam.G1E1RealEstate.services.base.BaseServiceImpl
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.stereotype.Service
import com.salesianostriana.dam.PuzzlesJavi.repositories.UsuarioRepository

@Service
class UsuarioService() : BaseServiceImpl<Usuario, Long, UsuarioRepository>() {
    fun findByUsername(username: String) = repositorio?.findByUsername(username)

}