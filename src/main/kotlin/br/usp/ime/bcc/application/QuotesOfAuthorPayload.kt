package br.usp.ime.bcc.application

import br.usp.ime.bcc.core.Quote

@kotlinx.serialization.Serializable
data class QuotesOfAuthorPayload(
    val author: String,
    val quotes: List<Quote>
)
