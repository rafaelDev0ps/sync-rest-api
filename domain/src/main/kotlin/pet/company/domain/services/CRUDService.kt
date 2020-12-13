package pet.company.domain.services

import pet.company.domain.entities.Pet
import pet.company.domain.factories.PetFactoryInterface
import pet.company.domain.payloads.CreatePetPayload
import pet.company.domain.repositories.CRUDPetRepositoryInterface
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CRUDService @Inject constructor(
        val petFactory:PetFactoryInterface,
        val crudRepository: CRUDPetRepositoryInterface
): CRUDServiceInterface {

    override fun create(payload: CreatePetPayload): Pet {
        val newPet = petFactory.build(payload.name, payload.race, payload.age)
        return crudRepository.create(newPet)
    }
}