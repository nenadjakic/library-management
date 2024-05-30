package com.github.nenadjakic.library.country.dto

import jakarta.validation.constraints.NotNull
import java.util.UUID

class CountryUpdateRequest : AbstractCountryRequest() {
    @NotNull
    var id: UUID? = null
}