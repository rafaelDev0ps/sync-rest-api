package pet.company.domain.payloads

data class UpdatePetPayload(val id: String, val name: String, val race: String, val age: Int)