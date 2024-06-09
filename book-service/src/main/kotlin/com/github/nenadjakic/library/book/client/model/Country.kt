package com.github.nenadjakic.library.book.client.model

import java.io.Serializable
import java.util.UUID

class Country(): Serializable {
    lateinit var id: UUID
    lateinit var alpha2Code: String
    lateinit var name: String

    constructor(id: UUID, alpha2Code: String, name: String) : this() {
        this.id = id
        this.alpha2Code = alpha2Code
        this.name = name
    }
}