package repositories

import entities.LineaDePedido
import org.springframework.data.jpa.repository.JpaRepository

interface LineaDePedidoRepository : JpaRepository<LineaDePedido, Long>