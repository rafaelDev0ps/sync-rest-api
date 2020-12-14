package pet.company.domain.services

import pet.company.domain.entities.Pet
import pet.company.domain.payloads.CreatePetPayload
import pet.company.domain.payloads.UpdatePetPayload

interface CRUDServiceInterface {
    fun create(payload: CreatePetPayload): Pet
    fun getAll(): List<Pet>
    fun update(payload: UpdatePetPayload): Pet
    fun delete(id: String): Boolean
}