package com.salesianostriana.dam.PuzzlesJavi.repositories

import com.salesianostriana.dam.PuzzlesJavi.entities.ImagenPuzzle
import org.springframework.data.jpa.repository.JpaRepository

interface ImagenPuzzleRepository: JpaRepository<ImagenPuzzle, Long> {
    abstract fun findByDeleteHash(deleteHash: String): ImagenPuzzle
}