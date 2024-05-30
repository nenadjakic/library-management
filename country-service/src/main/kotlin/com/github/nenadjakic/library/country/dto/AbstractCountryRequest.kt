package com.github.nenadjakic.library.country.dto

import jakarta.validation.constraints.NotNull

abstract class AbstractCountryRequest {
    @NotNull
    var alpha2Code: String? = null

    @NotNull
    var name: String? = null
}