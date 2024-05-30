package com.github.nenadjakic.library.book.client

import com.github.nenadjakic.library.book.client.model.Country
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
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
    @Cacheable(cacheNames = [ "country-cache" ], key = "#id")
    fun findById(@PathVariable("id") id: UUID): Country?
}