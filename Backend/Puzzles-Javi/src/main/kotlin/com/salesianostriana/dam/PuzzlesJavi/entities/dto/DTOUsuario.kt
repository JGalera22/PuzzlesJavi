package com.salesianostriana.dam.PuzzlesJavi.entities.dto

import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import java.time.LocalDate
import javax.persistence.Column
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class GetUsuarioDto (

    var id: Long?,

    @get:NotBlank(message = "{usuario.username.blank}")
    @get:Size( message = "{usuario.username.min}", min= 4, max= 20)
    var username : String,

    @get:Email( message = "{usuario.email.email}")
    var email: String,

    @get:NotBlank(message = "{usuario.nombreCompleto.blank}")
    var nombreCompleto: String,

    var fechaAlta: LocalDate,
    var Activo: Boolean,
    var Vip: Boolean,
)

fun Usuario.toGetUsuarioDto(): GetUsuarioDto=
    GetUsuarioDto(id, username, email, nombreCompleto, fechaAlta, activo, vip)



data class GetUsuarioRegistroDto(
    var id: Long?,
    var username : String,
    var email: String,
    var nombreCompleto: String,
    //ar roles: String?,
)

fun Usuario.toGetUsuarioRegistroDto(): GetUsuarioRegistroDto=
    GetUsuarioRegistroDto(id, username, email, nombreCompleto,/* roles.joinToString()*/)



data class GetLoginDto(
    var id: Long?,
    var username : String,
    var nombreCompleto: String,
    var email: String,
)

fun Usuario.toGetLoginDto(): GetLoginDto=
    GetLoginDto(id, username, nombreCompleto, email)

data class GetUsuarioPerfilDto(
    var username : String,
    var email: String,
    var nombreCompleto: String,
    var fechaAlta: LocalDate,
    var pedido: List<GetPedidoDto>,
)

fun Usuario.toGetUsuarioPerfilDto(): GetUsuarioPerfilDto{
    var listaPedidos: MutableList<GetPedidoDto> = mutableListOf()
    val pedido: List<GetPedidoDto> = listOf()
    pedido!!.forEach { i ->
        listaPedidos.add(GetPedidoDto(i.id, i.fechaPedido/*, i.total*/))
    }
    return GetUsuarioPerfilDto(username, email, nombreCompleto, fechaAlta, listaPedidos)
}

data class EditarUsuarioDto(
    @get:NotBlank(message = "{usuario.username.blank}")
    @get:Size(message = "{usuario.username.size}", min= 4, max= 20)
    @Column(unique = true)
    var username : String,
    @get:Size(message = "{usuario.password.size}", min= 8, max = 16)
    var passwd : String,
    @get:Email(message = "{usuario.email.email}")
    var email: String,
    @get:NotBlank(message = "{usuario.nombreCompleto.blank}")
    var nombreCompleto: String,
    var admin: Boolean?,
    var roles: String?,
)

data class UsuarioDTO(
    var username : String,
    var email : String,
    var nombreCompleto: String,
    var roles: String,
    val id: Long? = null
)

fun Usuario.toUserDTO() = UsuarioDTO(username, email, nombreCompleto, roles.joinToString(), id )


data class LoginUsuarioDto(
    var username : String,
    var password : String,
)

fun Usuario.loginUsuarioDto(): LoginUsuarioDto=
    LoginUsuarioDto(username, passwd)

