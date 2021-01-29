package pet.company.infra.repositories

import com.mongodb.client.MongoCollection
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.updateOneById
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

    override fun findAll(): List<T> {
        val list = collection.find()
        return list.toList()
    }

    override fun update(entity: T): T {
        collection.updateOneById(entity.id, entity.apply {
            updatedAt = Calendar.getInstance().time
        })
        return entity
    }

    override fun findPetById(id: String): T? {
        return collection.findOneById(id)
    }

    override fun delete(id: String): Boolean {
        val result = collection.deleteOneById(id)
        return result.wasAcknowledged()
    }
}