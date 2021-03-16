package repositories

import entities.Pedido
import org.springframework.data.jpa.repository.JpaRepository



interface PedidoRepository : JpaRepository<Pedido, Long>