package com.github.nenadjakic.library.book.dto

import jakarta.validation.constraints.NotNull
import java.util.UUID

class BookUpdateRequest : AbstractBookRequest() {
    @NotNull
    var id: UUID? = null
}