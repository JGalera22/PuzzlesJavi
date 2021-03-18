package services

import com.salesianostriana.dam.G1E1RealEstate.services.base.BaseServiceImpl
import entities.ImagenPuzzle
import entities.Puzzle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import repositories.ImagenPuzzleRepository
import upload.ImgurStorageService
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
