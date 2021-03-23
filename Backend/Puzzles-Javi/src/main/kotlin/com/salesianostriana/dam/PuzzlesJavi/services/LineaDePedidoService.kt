package com.salesianostriana.dam.PuzzlesJavi.services

import com.salesianostriana.dam.PuzzlesJavi.entities.LineaDePedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.repositories.LineaDePedidoRepository
import com.salesianostriana.dam.PuzzlesJavi.services.base.BaseServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service



@Service
class LineaDePedidoService(private val encoder: PasswordEncoder) : BaseServiceImpl<LineaDePedido, Long, LineaDePedidoRepository>() {
    fun getPuzzlesPedido(usuario: Usuario): List<LineaDePedido> = repositorio!!.findPuzzlesListaPedido(usuario)
}

