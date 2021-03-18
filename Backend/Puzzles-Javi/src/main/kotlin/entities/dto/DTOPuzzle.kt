package entities.dto

import entities.Puzzle
import entities.Usuario

data class EditPuzzleDto(
    var nombre: String,
    var categoria: String,
    var descripcion: String,
    var precio: Long,
    var numeroPiezas: Long,
)

data class GetPuzzleDto(
    var id: Long?,
    var nombre: String,
    var categoria: String,
    var precio: Long,
    var deseado: Boolean,
    var imagen: String?,
)

data class GetDetallePuzzleDto(
    var id: Long?,
    var nombre: String,
    var categoria: String,
    var descripcion: String,
    var precio: Long,
    var numeroPiezas: Long,
    var deseado: Boolean,
    var imagenes: List<GetImagenDetalleDto>,

)

fun Puzzle.toGetPuzzleDto(usuario: Usuario?): GetPuzzleDto {
    var deseado = false

    if (usuario != null){
        for (puzzle in usuario.puzzlesDeseados){
            if (puzzle.id == id){
                deseado = true
            }

        }
    }

    var url : String = "http://10.0.2.2:9000/files/"

    if(imagenes.isNotEmpty()){
        return GetPuzzleDto(
            id,
            nombre,
            categoria,
            precio,
            deseado,
            "${url}${imagenes[0].dataId}",
        )
    }
    else{
        return GetPuzzleDto(
            id,
            nombre,
            categoria,
            precio,
            deseado,
            "",
        )
    }

}

fun Puzzle.toGetDetallePuzzleDto(usuario: Usuario?): GetDetallePuzzleDto {

    var deseado = false

    if (usuario != null){
        for (puzzle in usuario.puzzlesDeseados){
            if (puzzle.id == id){
                deseado = true
            }

        }
    }

    var url : String = "http://10.0.2.2:9000/files/"

    var listaImagenes: MutableList<GetImagenDetalleDto> = mutableListOf()
    imagenes.forEach { i ->
        listaImagenes.add(GetImagenDetalleDto(i.id, "${url}${i.dataId}", i.deleteHash))
    }
    //var propietario = GetUsuarioPropiertarioDto(propietario!!.nombreCompleto, "https://robohash.org/${propietario!!.username}")

    return GetDetallePuzzleDto(
        id,
        nombre,
        categoria,
        descripcion,
        precio,
        numeroPiezas,
        deseado,
        listaImagenes
    )
}