package entities.dto

import entities.Usuario
import java.time.LocalDate

data class GetUsuarioDto (

    var id: Long?,
    var username : String,
    var email: String,
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
)

fun Usuario.toGetUsuarioRegistroDto(): GetUsuarioRegistroDto=
    GetUsuarioRegistroDto(id, username, email, nombreCompleto)



data class GetLoginDto(
    var id: Long?,
    var username : String,
    var nombreCompleto: String,
    var email: String,
)

fun Usuario.toGetLoginDto(): GetLoginDto=
    GetLoginDto(id, username, nombreCompleto, email)




data class LoginUsuarioDto(
    var username : String,
    var password : String,
    )

fun Usuario.loginUsuarioDto(): LoginUsuarioDto=
    LoginUsuarioDto(username, passwd)



data class GetUsuarioPropiertarioDto(
    var nombreCompleto: String,
    var avatar : String
)