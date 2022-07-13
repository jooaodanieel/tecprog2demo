package br.usp.ime.bcc

import br.usp.ime.bcc.application.datasource.QuotesRepositoryImpl
import br.usp.ime.bcc.application.ktor.configureDatabase
import br.usp.ime.bcc.application.ktor.configureHTTP
import br.usp.ime.bcc.application.ktor.configureRouting
import br.usp.ime.bcc.application.ktor.configureSerialization
import br.usp.ime.bcc.core.QuoteSearchService
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val db = configureDatabase()

    val quoteRepository = QuotesRepositoryImpl(db)
    val quoteSearchService = QuoteSearchService(quoteRepository)

    configureHTTP()
    configureSerialization()
    configureRouting(quoteSearchService)
}
