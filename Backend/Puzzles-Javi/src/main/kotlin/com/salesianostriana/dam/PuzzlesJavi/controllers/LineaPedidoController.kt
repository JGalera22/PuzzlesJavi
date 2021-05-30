package com.salesianostriana.dam.PuzzlesJavi.controllers

import com.salesianostriana.dam.PuzzlesJavi.entities.LineaDePedido
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.*
import com.salesianostriana.dam.PuzzlesJavi.error.ListEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.LineaDePedidoService
import com.salesianostriana.dam.PuzzlesJavi.services.PedidoService
import com.salesianostriana.dam.PuzzlesJavi.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/lineaPedido")
class LineaPedidoController {

    @Autowired
    lateinit var service: LineaDePedidoService

    @Autowired
    lateinit var pedidoService: PedidoService

    @Autowired
    lateinit var usuarioService: UsuarioService

    @GetMapping
    fun getAll(@AuthenticationPrincipal usuario: Usuario): List<GetLineaDePedidoDto> {
        return service.findAll()
            .map { it.toGetLineaDePedidoDto(usuario)}
            .takeIf { it!!.isNotEmpty() } ?: throw ListEntityNotFoundException(LineaDePedido::class.java)
    }

//    //Añadir a linea de pedido
//    @PostMapping("/lineaPedido/{id}")
//    fun addPuzzleALineaPedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario) : ResponseEntity<GetLineaDePedidoDto> {
//        var lineaDePedido = service.findById(id).orElse(null)
//        var puzzle = service.findById(id).orElse(null)
//        if (puzzle != null) {
//            service.getPuzzlesPedido(usuario)
//            //lineaDePedido.pedido.add(usuario)
//            service.save(puzzle)
//            return ResponseEntity.status(HttpStatus.CREATED).body(lineaDePedido.toGetLineaDePedidoDto(usuario))
//        } else {
//            throw SingleEntityNotFoundException(id.toString(), lineaDePedido::class.java)
//        }
//    }

    @DeleteMapping("/lineaPedido/{id}")
    fun deletePuzzleLineaPedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): ResponseEntity<Any> {
        usuario.puzzlesDeseados.forEach { p ->
            if (p.id == id) {
                usuario.puzzlesDeseados.remove(p)
                usuarioService.save(usuario)
            }
        }
        return ResponseEntity.noContent().build()

    }


}