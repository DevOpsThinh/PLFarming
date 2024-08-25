/*
Copyright (©) 2024 Nguyen Truong Thinh

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without limitation in the rights to use, copy, modify, merge,
publish, and/ or distribute copies of the Software in an educational or
personal context, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

Permission is granted to sell and/ or distribute copies of the Software in
a commercial context, subject to the following conditions:
Substantial changes: adding, removing, or modifying large parts, shall be
developed in the Software. Reorganizing logic in the software does not warrant
a substantial change.

This project and source code may use libraries or frameworks that are
released under various Open-Source licenses. Use of those libraries and
frameworks are governed by their own individual licenses.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package com.dev4ever.plfarm.webservice

// <editor-fold desc="ESSENTIAL PACKAGES">
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle

// </editor-fold>
/**
 * The [PLFarm] class, which inherits from the [CoroutineVerticle] abstract class
 * */
class PLFarm : CoroutineVerticle() {

  override suspend fun start() {
    val router = router()
    //
    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8888)

    println("HTTP server started on port 8888")
    println("open http://localhost:8888")
  }

  // <editor-fold desc="SUPPORT METHOD">
  /**
   * The [router] private method defines specific handlers for different HTTP
   * methods and URLs
   *
   * @return The [Router] interface
   * */
  private fun router(): Router = Router.router(vertx)
    .apply {
      route().handler(BodyHandler.create())
      // Endpoints
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
  // </editor-fold>
}
