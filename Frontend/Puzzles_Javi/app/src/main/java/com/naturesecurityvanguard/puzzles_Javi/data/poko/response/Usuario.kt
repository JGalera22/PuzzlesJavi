package com.naturesecurityvanguard.puzzles_Javi.data.poko.response

data class  Usuario(
        val username: String,
        val password: String,
        val email: String,
        val nombreCompleto: String,
        val fechaAlta: String,
        val activo: Boolean,
        val vip: Boolean,
        val Admin: Boolean,
        val id: Long? = null
)
