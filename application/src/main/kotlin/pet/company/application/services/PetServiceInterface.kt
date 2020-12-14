package pet.company.application.services

import pet.company.application.dto.CreatePetDTO
import pet.company.application.dto.UpdatePetDTO
import pet.company.domain.entities.Pet


interface PetServiceInterface {
    fun createPet(dto: CreatePetDTO): Pet
    fun findPets(): List<Pet>
    fun updatePet(dto: UpdatePetDTO): Pet
    fun removePet(id: String): Boolean
}