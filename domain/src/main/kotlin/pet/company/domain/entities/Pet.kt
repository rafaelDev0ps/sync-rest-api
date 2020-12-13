package pet.company.domain.entities

class Pet(id: String, name: String, race: String, age: Int) : Entity(id){
    var name: String
    var race: String
    var age: Int

    init {
        this.age = age
        this.name = name
        this.race = race
    }
}