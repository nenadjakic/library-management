package com.github.nenadjakic.library.country.service

import com.github.nenadjakic.library.country.entity.Country
import com.github.nenadjakic.library.country.repository.CountryRepository
import com.github.nenadjakic.library.shared.CrudService
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CountryService(
    private val countryRepository: CountryRepository
) : CrudService<Country, UUID> {

    override fun insert(entity: Country): Country = countryRepository.save(entity)

    override fun update(entity: Country): Country {
        val exists = countryRepository.existsById(entity.id!!)
        if (!exists) {
            throw EntityNotFoundException("Country with given id does not exist.")
        }
        return countryRepository.save(entity)
    }

    override fun delete(entity: Country) = countryRepository.delete(entity)

    override fun deleteById(id: UUID) = countryRepository.deleteById(id)

    override fun findAll(): List<Country> = countryRepository.findAll()

    override fun findPage(pageNumber: Int, pageSize: Int): Page<Country> = countryRepository.findAll(PageRequest.of(pageNumber, pageSize))

    override fun findById(id: UUID): Country? = countryRepository.findById(id).orElse(null)

    fun findByCode(code: String): Country? = countryRepository.findByAlpha2Code(code)

    fun findByName(pageNumber: Int, pageSize: Int, name: String): Page<Country> = countryRepository.findByNameStartingWithIgnoreCase(PageRequest.of(pageNumber, pageSize), name)
}