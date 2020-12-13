package pet.company.application.services

import pet.company.application.dto.CreatePetDTO
import pet.company.domain.entities.Pet


interface PetServiceInterface {
    fun createPet(dto: CreatePetDTO): Pet
}