package com.salesianostriana.dam.PuzzlesJavi.services

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.repositories.PedidoRepository
import com.salesianostriana.dam.PuzzlesJavi.services.base.BaseServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PedidoService(private val encoder: PasswordEncoder) : BaseServiceImpl<Pedido, Long, PedidoRepository>() {


}
