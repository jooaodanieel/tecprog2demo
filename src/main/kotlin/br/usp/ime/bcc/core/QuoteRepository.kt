package br.usp.ime.bcc.core

interface QuoteRepository {
    fun all(): List<Quote>

    fun findByAuthor(author: String): List<Quote>

    fun findById(id: Int): Quote
}
