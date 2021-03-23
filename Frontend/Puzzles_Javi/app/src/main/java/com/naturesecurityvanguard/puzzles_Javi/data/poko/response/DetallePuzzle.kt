package com.naturesecurityvanguard.puzzles_Javi.data.poko.response

class DetallePuzzle (
        val nombre: String,
        val categoria: String,
        val descripcion: String,
        val id: Long,
        val precio: Double,
        val numeroPiezas: Long,
        val imagenes: List<Imagen>,

)
