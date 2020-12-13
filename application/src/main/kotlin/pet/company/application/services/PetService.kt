package pet.company.application.services

import pet.company.domain.payloads.CreatePetPayload
import pet.company.application.dto.CreatePetDTO
import pet.company.domain.entities.Pet
import pet.company.domain.services.CRUDServiceInterface
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PetService @Inject constructor(
        private val crudService: CRUDServiceInterface
): PetServiceInterface{
    override fun createPet(dto: CreatePetDTO) : Pet {
        val createPayload = CreatePetPayload(dto.name, dto.race, dto.age)
        return crudService.create(createPayload)
    }

}