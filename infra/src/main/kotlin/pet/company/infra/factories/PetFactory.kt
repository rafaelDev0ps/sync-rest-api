package pet.company.infra.factories

import pet.company.domain.entities.Pet
import pet.company.domain.factories.PetFactoryInterface
import javax.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PetFactory : PetFactoryInterface {
    override fun build(name: String, race: String, age: Int): Pet {
        val uuid = UUID.randomUUID().toString()
        return Pet(uuid, name, race, age)
    }
}