package com.salesianostriana.dam.PuzzlesJavi.services


import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.stereotype.Service
import com.salesianostriana.dam.PuzzlesJavi.repositories.PuzzleRepository
import com.salesianostriana.dam.PuzzlesJavi.services.base.BaseServiceImpl


@Service
class PuzzleService(): BaseServiceImpl<Puzzle, Long, PuzzleRepository>() {

    fun getPuzzleFiltrados(categoria: String): List<Puzzle>? {
        var lista: List<Puzzle>? = repositorio?.findAll()
        if (categoria != "todas") {
            lista = lista?.filter { it.categoria.toLowerCase() == categoria.toLowerCase() }
        }
        return lista
    }

    fun getPuzzlesDeseados(usuario: Usuario): List<Puzzle> = usuario.puzzlesDeseados

}