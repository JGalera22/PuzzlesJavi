package com.naturesecurityvanguard.puzzles_Javi.data.poko.request

import java.time.LocalDate

data class UsuarioRequest(
    val email: String,
    val nombreCompleto: String,
    val passwd: String,
    val username: String
)