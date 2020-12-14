package pet.company.presentation.controller

import io.vertx.ext.web.RoutingContext
import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import pet.company.application.services.PetServiceInterface
import pet.company.application.dto.CreatePetDTO
import pet.company.application.dto.UpdatePetDTO
import pet.company.presentation.responses.CreatePetResponse
import pet.company.presentation.responses.UpdatePetResponse
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PetController @Inject constructor(
        private val petService: PetServiceInterface
){
    fun getPets(ctx: RoutingContext) {
        val petList = petService.findPets()
        val array = JsonArray()

        petList.forEach {
            try {
                array.add(json {
                    obj(
                            "name" to it.name,
                            "id" to it.id
                    )
                })
            } catch (ex: Exception) {
                ctx.response().end("Erro depois eu trato")
            }
        }

        ctx.response().putHeader("Content-Type", "application/json").setStatusCode(200).end(array.encode())
    }

    fun createPet(ctx: RoutingContext) {
        val name = ctx.bodyAsJson.getString("name")
        val race = ctx.bodyAsJson.getString("race")
        val age = ctx.bodyAsJson.getInteger("age")
        // TODO: fazer validações dos campos recebidos
        val createDTO = CreatePetDTO(name, race, age)

        val petCreated = petService.createPet(createDTO)
        ctx.response().end(Json.encode(CreatePetResponse(petCreated.id, petCreated.name)))
    }

    fun updatePet(ctx: RoutingContext) {
        val id = ctx.pathParam("id")
        val name = ctx.bodyAsJson.getString("name")
        val race = ctx.bodyAsJson.getString("race")
        val age = ctx.bodyAsJson.getInteger("age")

        val updateDTO = UpdatePetDTO(id, name, race, age)
        val updatedPet = petService.updatePet(updateDTO)

        ctx.response().end(Json.encode(UpdatePetResponse(updatedPet.id, updatedPet.name)))
    }

    fun deletePet(ctx: RoutingContext) {
        val id = ctx.pathParam("id")
        val petDeleted = petService.removePet(id)

        if (petDeleted) {
            ctx.response().end(json {
                obj (
                        "deleted" to petDeleted
                )
            }.toString())
        }
    }
}