package br.usp.ime.bcc.application.datasource

import org.jetbrains.exposed.dao.id.IntIdTable

object Quotes : IntIdTable() {
    val text = varchar("text", 255)
    val author = varchar("author", 255)
}
