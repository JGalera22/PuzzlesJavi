package com.salesianostriana.dam.PuzzlesJavi.controllers

import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.*
import com.salesianostriana.dam.PuzzlesJavi.error.ListEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*

import javax.validation.Valid


@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @Autowired
    lateinit var service: UsuarioService

    //Lista de usuarios
    @GetMapping
    fun getAll(): List<GetUsuarioDto> {
        return service.findAll()
            .map { it.toGetUsuarioDto() }
            .takeIf { it!!.isNotEmpty() } ?: throw ListEntityNotFoundException(Usuario::class.java)
    }


    //Detalle de un usuario
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): GetUsuarioPerfilDto {
            return service.findById(id)
                .map { it.toGetUsuarioPerfilDto() }
                .orElseThrow {
                    SingleEntityNotFoundException(id.toString(), Usuario::class.java)
                }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    fun getUsuarioPerfil(@AuthenticationPrincipal usuario: Usuario):GetUsuarioDto=
        usuario.toGetUsuarioDto()


    //editar un usuario
    @PutMapping("/{id}")
    fun edit(@Valid @RequestBody editarUsuario: EditarUsuarioDto, @PathVariable id: Long): GetUsuarioPerfilDto {
        return service.findById(id)
            .map { usuarioEncontrado ->
                usuarioEncontrado.passwd = editarUsuario.passwd
                usuarioEncontrado.email = editarUsuario.email
                usuarioEncontrado.nombreCompleto = editarUsuario.nombreCompleto
                //usuarioEncontrado.roles = editarUsuario.roles

                service.save(usuarioEncontrado).toGetUsuarioPerfilDto()
            }
            .orElseThrow { SingleEntityNotFoundException(id.toString(), Usuario::class.java) }
    }

    //borrar un usuario
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (service.existsById(id))
            service.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}