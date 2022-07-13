package br.usp.ime.bcc.core

@kotlinx.serialization.Serializable
data class Quote(
    val text: String,
    val author: String,
    val id: Int? = null
)
