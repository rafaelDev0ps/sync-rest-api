package pet.company.domain.repositories

import pet.company.domain.entities.Entity

interface RepositoryInterface<T> where T: Entity {
    fun create(entity: T): T
    fun findAll(): List<T>
    fun update(payload: T): T
    fun findPetById(id: String): T?
    fun delete(id: String): Boolean
}