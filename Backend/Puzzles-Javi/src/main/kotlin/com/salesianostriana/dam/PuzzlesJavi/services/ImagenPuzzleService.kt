package com.salesianostriana.dam.PuzzlesJavi.services


import com.salesianostriana.dam.PuzzlesJavi.entities.ImagenPuzzle
import com.salesianostriana.dam.PuzzlesJavi.entities.Puzzle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import com.salesianostriana.dam.PuzzlesJavi.repositories.ImagenPuzzleRepository
import com.salesianostriana.dam.PuzzlesJavi.services.base.BaseServiceImpl
import com.salesianostriana.dam.PuzzlesJavi.upload.ImgurStorageService
import java.util.*


@Service
class ImagenPuzzleService(
    private val imageStorageService: ImgurStorageService
): BaseServiceImpl<ImagenPuzzle, Long, ImagenPuzzleRepository>() {

    val logger: Logger = LoggerFactory.getLogger(ImagenPuzzleService::class.java)


    fun save(file: MultipartFile, puzzle: Puzzle) : ImagenPuzzle {
        var imagen: ImagenPuzzle
        var image : Optional<ImagenPuzzle> = Optional.empty()
        if (!file.isEmpty) {
            image = imageStorageService.store(file)
        }

        imagen = image.orElse(null)
        imagen.puzzle = puzzle
        return save(imagen)
    }

    override fun delete(i: ImagenPuzzle) {
        logger.debug("Eliminando la entidad $i")
        i?.let { it.deleteHash?.let { it1 -> imageStorageService.delete(it1) } }
        super.delete(i)

    }

    fun findByDeleteHash(deleteHash: String) = repositorio?.findByDeleteHash(deleteHash)



}
