package br.usp.ime.bcc

import br.usp.ime.bcc.application.ktor_plugins.configureDatabase
import br.usp.ime.bcc.application.ktor_plugins.configureHTTP
import br.usp.ime.bcc.application.ktor_plugins.configureRouting
import br.usp.ime.bcc.application.ktor_plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureRouting()
    configureDatabase()
}
