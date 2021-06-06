package com.salesianostriana.dam.PuzzlesJavi.repositories

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface PedidoRepository : JpaRepository<Pedido, Long>{
    @Query("select p.lineaPedido from Pedido p where p = :pedido")
    fun findPuzzlesListaPedido(@Param("pedido") usuario: Usuario): List<Puzzle>

}