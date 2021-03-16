package repositories

import entities.ImagenPuzzle
import org.springframework.data.jpa.repository.JpaRepository

interface ImagenPuzzleRepository: JpaRepository<ImagenPuzzle, Long> {
    abstract fun findByDeleteHash(deleteHash: String): ImagenPuzzle
}