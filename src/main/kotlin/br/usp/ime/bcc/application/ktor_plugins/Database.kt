package br.usp.ime.bcc.application.ktor_plugins

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    val url = environment.config.property("datasource.url").getString()
    val driver = environment.config.property("datasource.driver").getString()

    Database.connect(
        url = url,
        driver = driver
    )

    transaction {
        addLogger(StdOutSqlLogger)
    }
}
