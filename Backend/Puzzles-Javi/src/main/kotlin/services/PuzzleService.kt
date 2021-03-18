package services

import com.salesianostriana.dam.G1E1RealEstate.services.base.BaseServiceImpl
import entities.Puzzle
import entities.Usuario
import org.springframework.stereotype.Service
import repositories.PuzzleRepository


@Service
class PuzzleService(): BaseServiceImpl<Puzzle, Long, PuzzleRepository>() {

    fun getPuzzleFiltrados(categoria: String): List<Puzzle>? {
        var lista: List<Puzzle>? = repositorio?.findAll()
        if (categoria != "todas") {
            lista = lista?.filter { it.categoria.toString().toLowerCase() == categoria.toLowerCase() }
        }
        return lista
    }

    fun getPuzzlesDeseados(usuario: Usuario): List<Puzzle> = usuario.puzzlesDeseados

}