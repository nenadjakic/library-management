package com.github.nenadjakic.library.book.dto

import com.github.nenadjakic.library.book.client.model.Country
import java.util.UUID

data class PublisherResponse(
    val id: UUID,
    val name: String,
    val country: Country
)