package pet.company.domain.factories

import pet.company.domain.entities.Pet


interface PetFactoryInterface {
    fun build(name: String, race: String, age: Int): Pet
}