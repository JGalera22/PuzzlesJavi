package entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne


@Entity
class ImagenPuzzle (

    var dataId: String?,
    var deleteHash: String?,

    @ManyToOne
    var puzzle: Puzzle? = null,

    @Id @GeneratedValue val id: Long? = null
)