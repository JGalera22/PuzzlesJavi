package com.salesianostriana.dam.PuzzlesJavi.controllers



import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Usuario
import com.salesianostriana.dam.PuzzlesJavi.entities.dto.*
import com.salesianostriana.dam.PuzzlesJavi.error.DeseadoNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.ListEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.PedidoNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.error.SingleEntityNotFoundException
import com.salesianostriana.dam.PuzzlesJavi.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import com.salesianostriana.dam.PuzzlesJavi.upload.ImgurBadRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/puzzle")
class PuzzleController {

    @Autowired
    lateinit var service: PuzzleService

    @Autowired
    lateinit var imagenService: ImagenPuzzleService

    @Autowired
    lateinit var usuarioService: UsuarioService

    @Autowired
    lateinit var pedidoService: PedidoService


    //Lista de puzzles
    @GetMapping
    fun getAllPuzzles(
        @RequestParam(name = "cat", required = false, defaultValue = "todas") categoria: String,

    )
            : List<GetPuzzleDto> {
            return service.getPuzzleFiltrados(categoria)?.map { it.toGetPuzzleDto(null) }
                .takeIf { it!!.isNotEmpty() } ?: throw ListEntityNotFoundException(Puzzle::class.java)
    }

    //Lista de puzzles
    @GetMapping("/admin")
    fun getAllPuzzlesAdmin(
        @RequestParam(name = "cat", required = false, defaultValue = "todas") categoria: String,

        )
            : List<GetPuzzleDto> {
        return service.getPuzzleFiltrados(categoria)?.map { it.toGetPuzzleDto(null) }
            .takeIf { it!!.isNotEmpty() } ?: throw ListEntityNotFoundException(Puzzle::class.java)
    }

    //Detalle de un puzzle
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): GetDetallePuzzleDto {
            return service.findById(id)
                .map { it.toGetDetallePuzzleDto() }
                .orElseThrow {
                    SingleEntityNotFoundException(id.toString(), Puzzle::class.java)
                }
        }





    //Crear puzzle
    @PostMapping
    fun create(@Valid @RequestBody nuevoPuzzle: EditPuzzleDto): ResponseEntity<GetDetallePuzzleDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                service.save(
                    Puzzle(
                        nuevoPuzzle.nombre,
                        nuevoPuzzle.descripcion,
                        nuevoPuzzle.precio,
                        nuevoPuzzle.numeroPiezas,
                        nuevoPuzzle.categoria,
                    )
                ).toGetDetallePuzzleDto()
            )

    }

    //editar un puzzle
    @PutMapping("/{id}")
    fun edit(@Valid @RequestBody editarPuzzle: EditPuzzleDto, @PathVariable id: Long): GetDetallePuzzleDto {
        return service.findById(id)
            .map { puzzleEncontrado ->
                puzzleEncontrado.nombre = editarPuzzle.nombre
                puzzleEncontrado.descripcion = editarPuzzle.descripcion
                puzzleEncontrado.precio = editarPuzzle.precio
                puzzleEncontrado.numeroPiezas = editarPuzzle.numeroPiezas
                puzzleEncontrado.categoria = editarPuzzle.categoria

                service.save(puzzleEncontrado).toGetDetallePuzzleDto()
            }
            .orElseThrow { SingleEntityNotFoundException(id.toString(), Puzzle::class.java) }
    }

    //borrar un puzzle
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        if (service.existsById(id))
            service.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    //Añadir imagen a un puzzle
    @PostMapping("/{id}/img")
    fun createImage(@PathVariable id: Long, @RequestPart("file") file: MultipartFile): ResponseEntity<GetDetallePuzzleDto> {
        /*var auth : String = SecurityContextHolder.getContext().authentication.name
        var usuario : Optional<Usuario>? = usuarioService.findByUsername(auth)*/

        var puzzle: Puzzle = service.findById(id).orElse(null)

        if (puzzle != null) {
            try {
                imagenService.save(file, puzzle)
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(puzzle.toGetDetallePuzzleDto(/*usuario!!.get()*/))
            } catch (ex: ImgurBadRequest) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la subida de la imagen")
            }

        } else {
            throw SingleEntityNotFoundException(id.toString(), puzzle::class.java)
        }
    }

    //Borrar una imagen
    @DeleteMapping("/{id}/img/{hash}")
    fun deleteImage(@PathVariable id: Long, @PathVariable hash: String) : ResponseEntity<Any> {
        var puzzle:Puzzle = service.findById(id).orElse(null)
        if(puzzle != null) {
            var i = imagenService.findByDeleteHash(hash)
            println(i!!.dataId)
            imagenService.delete(i)
            puzzle.imagenes.remove(i!!.id)
            service.save(puzzle)

        }
        return ResponseEntity.noContent().build()
    }

    //Lista de deseados
    @GetMapping("/deseado")
    fun getDeseados(@AuthenticationPrincipal usuario: Usuario): List<GetPuzzleDto> {
        return service.getPuzzlesDeseados(usuario)
            .map { it.toGetPuzzleDto(usuario) }
            .takeIf { it.isNotEmpty() } ?: throw DeseadoNotFoundException(Puzzle::class.java)
    }

    //Añadir a lista de deseados
    @PostMapping("/deseado/{id}")
    fun addPuzzleDeseado(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario) : ResponseEntity<GetPuzzleDto> {
        var puzzle = service.findById(id).orElse(null)
        if (puzzle != null) {
            usuario.puzzlesDeseados.add(puzzle)
            usuarioService.save(usuario)
            return ResponseEntity.status(HttpStatus.CREATED).body(puzzle.toGetPuzzleDto(usuario))
        } else {
            throw SingleEntityNotFoundException(id.toString(), puzzle::class.java)
        }



    }

    @DeleteMapping("/deseado/{id}")
    fun deletePuzzleDeseado(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): ResponseEntity<Any> {
        usuario.puzzlesDeseados.forEach { p ->
            if (p.id == id) {
                usuario.puzzlesDeseados.remove(p)
                usuarioService.save(usuario)
            }
        }
        return ResponseEntity.noContent().build()

    }

    /*******************************************************************************/

//
//    //Lista de pedidos
//    @GetMapping("/pedido")
//    fun getPedidos(@AuthenticationPrincipal usuario: Usuario): List<GetPuzzleDto> {
//        return service.getPuzzlesPedido(usuario)
//            .map { it.toGetPuzzleDto(usuario) }
//            .takeIf { it.isNotEmpty() } ?: throw PedidoNotFoundException(Puzzle::class.java)
//    }
//
//    //Añadir a lista de pedidos
//    @PostMapping("/pedido/{id}")
//    fun addPuzzlePedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario) : ResponseEntity<GetPuzzleDto> {
//        var puzzle = service.findById(id).orElse(null)
//        if (puzzle != null) {
//            usuario.lineaPedido.add(puzzle)
//            usuarioService.save(usuario)
//            return ResponseEntity.status(HttpStatus.CREATED).body(puzzle.toGetPuzzleDto(usuario))
//        } else {
//            throw SingleEntityNotFoundException(id.toString(), puzzle::class.java)
//        }
//    }
//
//    @DeleteMapping("/pedido/{id}")
//    fun deletePuzzlePedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): ResponseEntity<Any> {
//        usuario.lineaPedido.forEach { p ->
//            if (p.id == id) {
//                usuario.lineaPedido.remove(p)
//                usuarioService.save(usuario)
//            }
//        }
//        return ResponseEntity.noContent().build()
//
//    }

    /*******************************************************************************/

//    //Lista de pedidos
//    @GetMapping("/pedido")
//    fun getPedidos(@AuthenticationPrincipal usuario: Usuario): List<GetPedidoDto> {
//        return pedidoService.getPuzzlesPedido(usuario)
//            .map { it.toGetPedidoDto(usuario)}
//            .takeIf { it.isNotEmpty() } ?: throw PedidoNotFoundException(Puzzle::class.java)
//    }
//
//    //Añadir a lista de pedidos
//    @PostMapping("/pedido/{id}")
//    fun addPuzzlePedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario) : ResponseEntity<GetLineaPedidoDto> {
//        var puzzle = service.findById(id).orElse(null)
//        var pedido = pedidoService.findById(id).orElse(null)
//        if (puzzle != null) {
//            pedido.listaPedidos.add(puzzle)
//            pedidoService.save(pedido)
//            return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toGetLineaPedidoDto(usuario))
//        } else {
//            throw SingleEntityNotFoundException(id.toString(), pedido::class.java)
//        }
//    }
//
//    @DeleteMapping("/pedido/{id}")
//    fun deletePuzzlePedido(@PathVariable id: Long, @AuthenticationPrincipal usuario: Usuario): ResponseEntity<Any> {
//        var pedido = pedidoService.findById(id).orElse(null)
//        pedido.listaPedidos.forEach{ p ->
//            if (p.id == id) {
//                pedido.listaPedidos.remove(p)
//                pedidoService.save(pedido)
//            }
//        }
//        return ResponseEntity.noContent().build()
//
//    }

}