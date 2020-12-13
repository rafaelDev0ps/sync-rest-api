package pet.company.presentation.utils

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import java.lang.Exception

fun Route.handlerRequests(fn: (ctx:RoutingContext) -> Unit): Route {
    return handler {ctx ->
        try {
            fn(ctx)
        } catch (ex: Exception) {
            ctx.fail(ex)
        }
    }
}