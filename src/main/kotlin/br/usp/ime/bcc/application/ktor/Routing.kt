package br.usp.ime.bcc.application.ktor

import br.usp.ime.bcc.application.QuotesOfAuthorPayload
import br.usp.ime.bcc.core.QuoteSearchService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting(quoteSearchService: QuoteSearchService) {
    routing {
        get("/quotes") {
            val allQuotes = quoteSearchService.all()
            call.respond(
                HttpStatusCode.OK,
                mapOf("quotes" to allQuotes)
            )
        }

        get("/quotes/{id}") {
            val id = call.parameters["id"]

            try {
                val quote = quoteSearchService.one(id)
                call.respond(
                    HttpStatusCode.OK,
                    mapOf("quote" to quote)
                )
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to e.message)
                )
            }
        }

        get("/authors/{author}/quotes") {
            val author = call.parameters["author"]

            try {
                val quotesFromAuthor = quoteSearchService.byAuthor(author)

                call.respond(
                    HttpStatusCode.OK,
                    QuotesOfAuthorPayload(
                        author = author!!,
                        quotes = quotesFromAuthor
                    )
                )
            } catch (e: IllegalArgumentException) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to e.message)
                )
            }
        }
    }
}
