package pet.company.presentation.endpoints

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import pet.company.presentation.controller.PetController
import pet.company.presentation.utils.handlerRequests
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@ApplicationScoped
class PetEndpoints @Inject constructor(
        private val controller: PetController
){
    fun appRoutes(@Observes router: Router) {
        router.apply {
            route().apply {
                handler(BodyHandler.create())
                consumes("application/json")
                produces("application/json")
            }

            get("/pets").apply {
                handlerRequests(controller::getPets)
            }

            post("/pet").apply {
                handlerRequests(controller::createPet)
            }

            put("/pet").apply {
                handlerRequests(controller::updatePet)
            }

            delete("/pet").apply {
                handlerRequests(controller::deletePet)
            }
        }
    }
}