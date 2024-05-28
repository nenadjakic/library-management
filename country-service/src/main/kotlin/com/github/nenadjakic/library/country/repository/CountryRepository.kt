package com.github.nenadjakic.library.country.repository

import com.github.nenadjakic.library.country.entity.Country
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CountryRepository : JpaRepository<Country, UUID> {
    fun findByAlpha2Code(code: String): Country?

    fun findByNameStartingWithIgnoreCase(name: String): List<Country>
    fun findByNameStartingWithIgnoreCase(pageable: Pageable, name: String): Page<Country>
}