package com.github.nenadjakic.library.book.client

import com.github.nenadjakic.library.book.client.model.Country
import feign.Param
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(
    value = "countryServiceClient",
    url = "http://localhost:6061",
    path="/country",
    dismiss404 = true
)
interface CountryClient {
    @GetMapping()
    fun findAll(): List<Country>;

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID): Country?
}