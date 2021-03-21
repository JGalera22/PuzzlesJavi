package com.salesianostriana.dam.PuzzlesJavi.repositories

import com.salesianostriana.dam.PuzzlesJavi.entities.LineaDePedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LineaDePedidoRepository : JpaRepository<LineaDePedido, Long> {
    @Query("select u.puzzlesDeseados from Usuario u where u = :usuario")
    fun findPuzzlesListaPedido(@Param("pedido") usuario: Usuario): List<LineaDePedido>
}