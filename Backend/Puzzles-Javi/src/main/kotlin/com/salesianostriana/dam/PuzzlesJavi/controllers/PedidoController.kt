package com.salesianostriana.dam.PuzzlesJavi.controllers

import com.salesianostriana.dam.PuzzlesJavi.entities.Pedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.*
import com.salesianostriana.dam.PuzzlesJavi.error.DeseadoNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.ListEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.PedidoNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.PedidoService
import com.salesianostriana.dam.PuzzlesJavi.services.PuzzleService
import com.salesianostriana.dam.PuzzlesJavi.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/pedido")
class PedidoController {

    @Autowired
    lateinit var usuarioService: UsuarioService

    @Autowired
    lateinit var service: PedidoService

    @Autowired
    lateinit var puzzleService: PuzzleService



    @GetMapping ("/all")
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

    //Lista de pedidos
    @GetMapping
    fun getPedidos(@AuthenticationPrincipal usuario: Usuario): List<Unit> {
        return service.getPuzzlesPedido(usuario)
            .map { it.toGetPedidoDto(usuario)}
            .takeIf { it.isNotEmpty() } ?: throw PedidoNotFoundException(Pedido::class.java)
    }


    //Añadir a lista de pedidos
    @PostMapping("/{id}")
    fun addPuzzlePedido(@PathVariable id: Long, pedido_id: Long, @AuthenticationPrincipal usuario: Usuario) : ResponseEntity<GetLineaPedidoDto> {
        var puzzle = puzzleService.findById(id).orElse(null)
        lateinit var pedido: Pedido
        if (puzzle != null) {
            pedido.lineaPedido.add(puzzle)
            service.save(pedido)
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toGetLineaPedidoDto(usuario))
        } else {
            throw SingleEntityNotFoundException(id.toString(), pedido::class.java)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePuzzlePedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): ResponseEntity<Any> {
        var pedido = service.findById(id).orElse(null)
        pedido.lineaPedido.forEach{ p ->
            if (p.id == id) {
                pedido.lineaPedido.remove(p)
                service.save(pedido)
            }
        }
        return ResponseEntity.noContent().build()

    }
}