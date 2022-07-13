package br.usp.ime.bcc.core

class QuoteSearchService(
    private val repository: QuoteRepository
) {

    fun all() = repository.all()

    fun byAuthor(author: String?): List<Quote> {
        if (author == null || author == "") throw IllegalArgumentException("Author name must be provided")

        return repository.findByAuthor(author)
    }

    fun one(id: String?): Quote {
        if (id == null) throw IllegalArgumentException("ID must be provided")

        val intId: Int = id.toInt()

        if (intId < 1) throw IllegalArgumentException("ID must be greater than 0")

        return repository.findById(intId)
    }
}
