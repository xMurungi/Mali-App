package com.joses.mali.server

//import io.ktor.application.*
//import io.ktor.features.*
//import io.ktor.http.*
//import io.ktor.request.*
//import io.ktor.response.*
//import io.ktor.routing.*
//import io.ktor.server.engine.*
//import io.ktor.server.netty.*
//import kotlinx.coroutines.runBlocking
//
//fun main() {
//    embeddedServer(Netty, port = 5000) {
//        install(CallLogging)
//
//        routing {
//            post("/mpesa/callback") {
//                val requestBody = call.receive<String>()
//                println("M-Pesa Callback Received: $requestBody")
//                call.respond(HttpStatusCode.OK, "Callback received")
//            }
//        }
//    }.start(wait = true)
//}
