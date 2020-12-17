package pet.company.domain.services

import pet.company.domain.entities.Pet
import pet.company.domain.factories.PetFactoryInterface
import pet.company.domain.payloads.CreatePetPayload
import pet.company.domain.payloads.UpdatePetPayload
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

    override fun getAll(): List<Pet> {
        return crudRepository.findAll()
    }

    override fun update(payload: UpdatePetPayload): Pet {
        val petFound = crudRepository.findPetById(payload.id)
                ?: throw Exception("pet not found")

        petFound.apply {
            this.age = payload.age
            this.name = payload.name
            this.race = payload.race
        }

        return crudRepository.update(petFound)
    }

    override fun delete(id: String): Boolean {
        return crudRepository.delete(id)
    }
}