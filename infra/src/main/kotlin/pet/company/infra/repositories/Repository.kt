package pet.company.infra.repositories

import com.mongodb.client.MongoCollection
import pet.company.domain.entities.Entity
import pet.company.domain.repositories.RepositoryInterface
import java.util.*


open class Repository<T>() : RepositoryInterface<T> where T: Entity {
    private lateinit var collection: MongoCollection<T>

    constructor(collection: MongoCollection<T>) : this() {
        this.collection = collection
    }

    override fun create(entity: T): T {
        collection.insertOne(entity.apply {
            val currentDate = Calendar.getInstance().time
            updatedAt = currentDate
            createdAt = currentDate
        })
        return entity
    }
}