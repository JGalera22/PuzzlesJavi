package com.naturesecurityvanguard.puzzles_Javi.data.poko.request

data class PuzzleRequest(
    val nombre: String,
    val categoria: String,
    val descripcion: String,
    val precio: Double,
    val numeroPiezas: Long,
)