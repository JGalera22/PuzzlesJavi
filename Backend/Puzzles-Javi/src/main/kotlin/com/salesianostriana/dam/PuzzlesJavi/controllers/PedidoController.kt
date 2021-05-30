package com.salesianostriana.dam.PuzzlesJavi.controllers

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.*
import com.salesianostriana.dam.PuzzlesJavi.error.ListEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.PedidoService
import com.salesianostriana.dam.PuzzlesJavi.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
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

    @Autowired
    lateinit var service: PedidoService

    @GetMapping
    fun getAll(@AuthenticationPrincipal usuario: Usuario): List<GetPedidoDto> {
        return service.findAll()
            .map { it.toGetPedidoDto(usuario) }
            .takeIf { it!!.isNotEmpty() } ?: throw ListEntityNotFoundException(Pedido::class.java)
    }

    //Detalle de un pedido
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): GetPedidoDetalleDto {
            return service.findById(id)
                .map { it.toGetPedidoDetalleDto(usuario) }
                .orElseThrow {SingleEntityNotFoundException(id.toString(), Pedido::class.java)}
    }


}