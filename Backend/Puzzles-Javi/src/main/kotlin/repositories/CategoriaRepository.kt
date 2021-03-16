package repositories

import entities.Categoria
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriaRepository : JpaRepository<Categoria, Long>