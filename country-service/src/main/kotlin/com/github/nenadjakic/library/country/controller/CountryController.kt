package com.github.nenadjakic.library.country.controller

import com.github.nenadjakic.library.country.dto.CountryAddRequest
import com.github.nenadjakic.library.country.dto.CountryUpdateRequest
import com.github.nenadjakic.library.country.entity.Country
import com.github.nenadjakic.library.country.service.CountryService
import com.github.nenadjakic.library.shared.CrudController
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/country")
open class CountryController(
    private val modelMapper: ModelMapper,
    private val countryService: CountryService
) : CrudController<CountryAddRequest, CountryUpdateRequest, Country, UUID> {
    override fun create(model: CountryAddRequest): ResponseEntity<Void> {
        val country = modelMapper.map(model, Country::class.java)
        val createdCountry = countryService.insert(country)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdCountry.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }

    override fun update(model: CountryUpdateRequest): ResponseEntity<Void> {
        val country = modelMapper.map(model, Country::class.java)
        countryService.update(country)

        return ResponseEntity.noContent().build()
    }

    override fun delete(id: UUID): ResponseEntity<Void> {
        countryService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    override fun findAll(): ResponseEntity<List<Country>> {
        val countries = countryService.findAll()

        return ResponseEntity.ok(countries)
    }

    override fun findPage(pageNumber: Int, pageSize: Int?): ResponseEntity<Page<Country>> {
        val page = countryService.findPage(pageNumber, pageSize ?: 20)
        val response = page.map { modelMapper.map(it, Country::class.java) }
        return ResponseEntity.ok(response)
    }

    override fun findById(id: UUID): ResponseEntity<Country> {
        val country = countryService.findById(id)
        val response = country?.let { modelMapper.map(country, Country::class.java) }

        return ResponseEntity.ofNullable(response)
    }

    @GetMapping(value = ["code/{code}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByCode(@PathVariable code: String): ResponseEntity<Country> {
        val country = countryService.findByCode(code)
        val response = country?.let { modelMapper.map(country, Country::class.java) }

        return ResponseEntity.ofNullable(response)
    }

    @GetMapping(value = ["/page/{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByName(@PathVariable name: String,
                   @RequestParam pageNumber: Int,
                   @RequestParam(required = false) pageSize: Int?): ResponseEntity<Page<Country>> {
        val page = countryService.findByName(pageNumber, pageSize ?: 20, name)
        val response = page.map { modelMapper.map(it, Country::class.java) }
        return ResponseEntity.ok(response)
    }
}