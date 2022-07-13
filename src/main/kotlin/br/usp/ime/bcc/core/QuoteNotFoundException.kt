package br.usp.ime.bcc.core

class QuoteNotFoundException(id: Int) : RuntimeException("Quote id=$id not found")
