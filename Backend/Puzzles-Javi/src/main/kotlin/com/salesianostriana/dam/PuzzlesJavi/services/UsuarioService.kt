package com.salesianostriana.dam.PuzzlesJavi.services



import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.EditarUsuarioDto
import org.springframework.stereotype.Service
import com.salesianostriana.dam.PuzzlesJavi.repositories.UsuarioRepository
import com.salesianostriana.dam.PuzzlesJavi.services.base.BaseServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

@Service
class UsuarioService(private val encoder: PasswordEncoder) : BaseServiceImpl<Usuario, Long, UsuarioRepository>() {

    fun create(newUser: EditarUsuarioDto): Optional<Usuario> {
        if(findByUsername(newUser.username)!!.isPresent)
            return Optional.empty()
        return Optional.of(
            with(newUser) {
                repositorio!!.save(
                    Usuario(username, encoder.encode(passwd), email, nombreCompleto, "USER")
                )
            }
        )
    }

    fun findByUsername(username: String) = repositorio?.findByUsername(username)

}