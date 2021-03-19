package com.naturesecurityvanguard.puzzles_Javi.data.poko.response

data class LoginResponse(
    val token: String,
    val usuario: UsuarioLoginResponse
)