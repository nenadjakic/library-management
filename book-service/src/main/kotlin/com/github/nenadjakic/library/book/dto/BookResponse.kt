package com.github.nenadjakic.library.book.dto

import java.util.UUID

class BookResponse {

    lateinit var id: UUID

    lateinit var isbn: String

    lateinit var title: String

    var edition: Int? = null

    var pageCount: Int? = null
}