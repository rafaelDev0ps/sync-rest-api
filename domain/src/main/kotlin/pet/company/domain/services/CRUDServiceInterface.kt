package pet.company.domain.services

import pet.company.domain.entities.Pet
import pet.company.domain.payloads.CreatePetPayload

interface CRUDServiceInterface {
    fun create(payload: CreatePetPayload): Pet
}