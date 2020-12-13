package pet.company.infra.ioc

import com.mongodb.ConnectionString
import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import pet.company.domain.entities.Pet
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class MongoConfiguration {
    @Produces
    fun getPetCollection(database: MongoDatabase): MongoCollection<Pet>{
        return database.getCollection<Pet>("pets")
    }

    @Produces
    fun getClient(connectionString: ConnectionString): MongoClient {
        return KMongo.createClient(connectionString)
    }

    @Produces
    fun getDatabase(client: MongoClient): MongoDatabase {
        return client.getDatabase("rest")
    }


    @Produces
    fun getConnectionString(): ConnectionString {
        var connectionString = "mongodb://127.0.0.1:27017"

        try {
            connectionString = System.getenv("MONGO_CONNECTION_STRING")
        } catch (e: Exception) {
            println("mongodb localhost")
        }

        return ConnectionString(connectionString)
    }

}