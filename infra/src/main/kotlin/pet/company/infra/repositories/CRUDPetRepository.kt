package pet.company.infra.repositories

import com.mongodb.client.MongoCollection
import pet.company.domain.entities.Pet
import pet.company.domain.repositories.CRUDPetRepositoryInterface
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CRUDPetRepository @Inject constructor(
    private val collection: MongoCollection<Pet>
) : CRUDPetRepositoryInterface, Repository<Pet>(collection) {}
