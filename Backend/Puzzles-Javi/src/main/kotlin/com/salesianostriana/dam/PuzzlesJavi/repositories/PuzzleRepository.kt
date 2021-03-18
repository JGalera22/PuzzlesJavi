package com.salesianostriana.dam.PuzzlesJavi.repositories
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import org.springframework.data.jpa.repository.JpaRepository


interface PuzzleRepository : JpaRepository<Puzzle, Long>
