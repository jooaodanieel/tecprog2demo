package br.usp.ime.bcc.application.ktor

import br.usp.ime.bcc.application.datasource.Quotes
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase(): Database {
    val url = environment.config.property("datasource.url").getString()
    val driver = environment.config.property("datasource.driver").getString()

    val db = Database.connect(
        url = url,
        driver = driver
    )

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Quotes)
    }

    seed(db)

    return db
}

private fun seed(db: Database) = transaction(db) {
    Quotes.deleteAll()

    Quotes.insert {
        it[text] = "The computer was born to solve problems that did not exist before."
        it[author] = "BillGates"
    }

    Quotes.insert {
        it[text] = "If you think your users are idiots, only idiots will use it."
        it[author] = "LinusTorvalds"
    }

    Quotes.insert {
        it[text] = "Computers are good at following instructions, but not at reading your mind."
        it[author] = "DonaldKnuth"
    }

    Quotes.insert {
        it[text] = "Your most unhappy customers are your greatest source of learning."
        it[author] = "BillGates"
    }
}
