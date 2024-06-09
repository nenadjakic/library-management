package com.github.nenadjakic.library.book.client

import com.github.nenadjakic.library.book.client.model.Country
import com.github.nenadjakic.library.book.configuration.PUBLISHER_CACHE_COUNTRY_GENERAL
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@FeignClient(value = "countryServiceClient")
interface CountryClient {
    @GetMapping
    fun findAll(): List<Country>

    @GetMapping("/{id}")
    @Cacheable(cacheNames = [ PUBLISHER_CACHE_COUNTRY_GENERAL ], key = "#id", unless = "#result == null")
    fun findById(@PathVariable("id") id: UUID): Country?
}