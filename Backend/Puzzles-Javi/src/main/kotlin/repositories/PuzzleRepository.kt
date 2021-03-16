package repositories
import entities.Puzzle
import org.springframework.data.jpa.repository.JpaRepository


interface PuzzleRepository : JpaRepository<Puzzle, Long>
