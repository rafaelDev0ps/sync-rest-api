package pet.company.presentation.controller

import io.vertx.ext.web.RoutingContext
import io.vertx.core.json.Json
import pet.company.application.services.PetServiceInterface
import pet.company.application.dto.CreatePetDTO
import pet.company.presentation.responses.CreatePetResponse
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class PetController @Inject constructor(
        private val petService: PetServiceInterface
){
    fun getPets(ctx: RoutingContext) {
        ctx.response().end("get")
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
        ctx.response().end("update")
    }

    fun deletePet(ctx: RoutingContext) {
        ctx.response().end("delete")
    }
}