package pet.company.domain.repositories

import pet.company.domain.entities.Pet

interface CRUDPetRepositoryInterface : RepositoryInterface<Pet>{
    override fun create(pet: Pet): Pet
}