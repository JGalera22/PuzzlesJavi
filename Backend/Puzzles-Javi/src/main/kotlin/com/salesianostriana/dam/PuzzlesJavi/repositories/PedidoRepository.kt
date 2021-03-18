package com.salesianostriana.dam.PuzzlesJavi.repositories

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import org.springframework.data.jpa.repository.JpaRepository



interface PedidoRepository : JpaRepository<Pedido, Long>