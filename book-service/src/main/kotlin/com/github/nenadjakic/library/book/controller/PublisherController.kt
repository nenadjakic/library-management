package com.github.nenadjakic.library.book.controller

import com.github.nenadjakic.library.book.client.CountryClient
import com.github.nenadjakic.library.book.client.model.Country
import com.github.nenadjakic.library.book.dto.PublisherResponse
import com.github.nenadjakic.library.book.service.PublisherService
import com.github.nenadjakic.library.shared.async.ReadController
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/publisher")
open class PublisherController(
    private val modelMapper: ModelMapper,
    private val publisherService: PublisherService,
    private val countryClient: CountryClient
) : ReadController<PublisherResponse, UUID> {
    override suspend fun findAll(): ResponseEntity<List<PublisherResponse>> = coroutineScope {
        val publishers = publisherService.findAll()
        val countryIds = publishers.parallelStream().map { it.countryId }.collect(Collectors.toList())
        val countries = async { findCountriesByIds(countryIds) }.await()
        val response = mutableListOf<PublisherResponse>()
        publishers.forEach {
            response.add(PublisherResponse(it.id!!, it.name, findCountryById(it.countryId, countries)))
        }

        return@coroutineScope ResponseEntity.ok(response)
    }

    override suspend fun findPage(pageNumber: Int, pageSize: Int?): ResponseEntity<Page<PublisherResponse>> {
        TODO("Not yet implemented")
    }

    @GetMapping("/{id}")
    override suspend fun findById(@PathVariable id: UUID): ResponseEntity<PublisherResponse> = coroutineScope {
        val publisher = publisherService.findById(id)
        val response = publisher?.let {
            val country = async { findCountryById(it.countryId) }.await()
            if (country != null) {
                return@let PublisherResponse(publisher.id!!, publisher.name, country)
            } else {
                return@let null
            }
        }

        return@coroutineScope ResponseEntity.ofNullable(response)
    }

    suspend fun findCountryById(id: UUID): Country? = countryClient.findById(id)

    suspend fun findCountriesByIds(ids: List<UUID>): List<Country> = mutableListOf()

    fun findCountryById(id: UUID, countries: List<Country>): Country = countries.parallelStream().filter { it.id == id }.findAny().get()
}