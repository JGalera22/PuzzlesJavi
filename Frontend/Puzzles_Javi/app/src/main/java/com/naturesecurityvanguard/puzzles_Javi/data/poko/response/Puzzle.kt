package com.naturesecurityvanguard.puzzles_Javi.data.poko.response

data class Puzzle(
        val nombre: String,
        val categoria: String,
        val precio: Double,
        val imagen: String,
        val deseado: Boolean,
        val id: Long,
)