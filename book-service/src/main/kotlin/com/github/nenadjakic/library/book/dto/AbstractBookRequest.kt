package com.github.nenadjakic.library.book.dto

import jakarta.validation.constraints.NotNull

abstract class AbstractBookRequest {
    @NotNull
    var isbn: String? = null

    @NotNull
    var title: String? = null

    var edtition: Int? = null
    var pageCount: Int? = null
}