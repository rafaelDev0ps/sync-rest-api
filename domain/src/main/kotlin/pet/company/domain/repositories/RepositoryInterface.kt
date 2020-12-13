package pet.company.domain.repositories

import pet.company.domain.entities.Entity

interface RepositoryInterface<T> where T: Entity {
    fun create(entity: T): T
}