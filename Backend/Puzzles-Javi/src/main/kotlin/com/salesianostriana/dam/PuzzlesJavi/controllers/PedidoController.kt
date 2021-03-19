package com.salesianostriana.dam.PuzzlesJavi.controllers

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.GetDetallePuzzleDto
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.GetPedidoDto
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.toGetDetallePuzzleDto
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.toGetPedidoDto
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.PedidoService
import com.salesianostriana.dam.PuzzlesJavi.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/pedido")
class PedidoController {

    @Autowired
    lateinit var usuarioService: UsuarioService

    lateinit var service: PedidoService

    //Detalle de un puzzle
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): GetPedidoDto {
        var auth: String = SecurityContextHolder.getContext().authentication.name
        var usuario: Optional<Usuario>? = usuarioService.findByUsername(auth)
        if (usuario!!.isPresent) {
            return service.findById(id)
                .map { it.toGetPedidoDto(usuario!!.get()) }
                .orElseThrow {SingleEntityNotFoundException(id.toString(), Puzzle::class.java)}
        }else {
            throw SingleEntityNotFoundException(id.toString(), Pedido::class.java)
        }
    }
}