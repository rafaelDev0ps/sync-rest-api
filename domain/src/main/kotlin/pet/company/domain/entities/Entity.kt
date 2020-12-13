package pet.company.domain.entities

import java.util.*

abstract class Entity (val _id: String) {
    val id get() = _id

    var updatedAt: Date = Calendar.getInstance().time
    var createdAt: Date = Calendar.getInstance().time
}