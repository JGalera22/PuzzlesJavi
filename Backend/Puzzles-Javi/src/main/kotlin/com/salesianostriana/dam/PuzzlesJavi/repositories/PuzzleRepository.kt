package com.salesianostriana.dam.PuzzlesJavi.repositories
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.websocket.server.PathParam


interface PuzzleRepository : JpaRepository<Puzzle, Long>{
    @Query("select u.puzzlesDeseados from Usuario u where u = :usuario")
    fun findPuzzlesDeseados(@Param("usuario") usuario: Usuario):List<Puzzle>

//    @Query("select p.lineaPedido from Pedido p where p = :pedido")
//    fun findPuzzlesListaPedido(@Param("usuario") usuario: Usuario): List<Puzzle>

    @Query("select u.lineaPedido from Usuario u where u = :usuario")
    fun findPuzzlesListaPedido(@Param("usuario") usuario: Usuario): List<Puzzle>


}

