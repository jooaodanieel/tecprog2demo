package br.usp.ime.bcc.application.datasource

import br.usp.ime.bcc.core.Quote
import br.usp.ime.bcc.core.QuoteNotFoundException
import br.usp.ime.bcc.core.QuoteRepository
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class QuotesRepositoryImpl(
    private val db: Database
) : QuoteRepository {
    override fun all(): List<Quote> = transaction(db) {
        Quotes
            .selectAll()
            .map { row -> rowToQuote(row) }
    }

    override fun findByAuthor(author: String): List<Quote> = transaction(db) {
        Quotes
            .select { Quotes.author eq author }
            .map { row -> rowToQuote(row) }
    }

    override fun findById(id: Int): Quote = transaction(db) {
        try {
            Quotes
                .select { Quotes.id eq id }
                .map { row -> rowToQuote(row) }
                .first()
        } catch (_: NoSuchElementException) {
            throw QuoteNotFoundException(id)
        }
    }

    private fun rowToQuote(row: ResultRow): Quote = Quote(
        id = row[Quotes.id].value,
        text = row[Quotes.text],
        author = row[Quotes.author]
    )
}
