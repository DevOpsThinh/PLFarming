package com.dev4ever.plfarm.webService

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle

class PLFarm : CoroutineVerticle() {

  override suspend fun start() {
    val router = router()

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8888)

    println("HTTP server started on port 8888")
    println("open http://localhost:8888")
  }

  private fun router(): Router = Router.router(vertx)
    .apply {
      route().handler(BodyHandler.create())

      get("/status").handler { ctx ->
        val json = json {
          obj(
            "status" to "OK",
          )
        }

        ctx.response()
          .setStatusCode(200)
          .end(json.toString())
      }
  }
}
