package com.github.nenadjakic.library.country.service

import com.github.nenadjakic.library.country.COUNTRY_CACHE_COUNTRY_GENERAL
import com.github.nenadjakic.library.country.COUNTRY_CACHE_COUNTRY_PAGEABLE
import com.github.nenadjakic.library.country.entity.Country
import com.github.nenadjakic.library.country.repository.CountryRepository
import com.github.nenadjakic.library.shared.CrudService
import jakarta.persistence.EntityNotFoundException
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CountryService(
    private val countryRepository: CountryRepository
) : CrudService<Country, UUID> {

    @CachePut(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "#result.id", unless = "#result == null")
    @CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_PAGEABLE ], allEntries = true )
    override fun insert(entity: Country): Country {
        entity.id = UUID.randomUUID()
        return countryRepository.save(entity)
    }

    @CachePut(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "#entity.id", unless = "#result == null")
    @CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_PAGEABLE ], allEntries = true )
    override fun update(entity: Country): Country {
        val exists = countryRepository.existsById(entity.id!!)
        if (!exists) {
            throw EntityNotFoundException("Country with given id does not exist.")
        }
        return countryRepository.save(entity)
    }
    @Caching(evict = [
        CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "#entity.id"),
        CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_PAGEABLE ], allEntries = true )
    ])
    override fun delete(entity: Country) = countryRepository.delete(entity)

    @Caching(evict = [
        CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "#entity.id"),
        CacheEvict(cacheNames = [ COUNTRY_CACHE_COUNTRY_PAGEABLE ], allEntries = true )
    ])
    override fun deleteById(id: UUID) = countryRepository.deleteById(id)

    override fun findAll(): List<Country> = countryRepository.findAll()

    @Cacheable(cacheNames = [ COUNTRY_CACHE_COUNTRY_PAGEABLE ], key = "#pageNumber + '-' + #pageSize")
    override fun findPage(pageNumber: Int, pageSize: Int): Page<Country> = countryRepository.findAll(PageRequest.of(pageNumber, pageSize))

    @Cacheable(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "#id", unless = "#result == null")
    override fun findById(id: UUID): Country? = countryRepository.findById(id).orElse(null)

    @Cacheable(cacheNames = [ COUNTRY_CACHE_COUNTRY_GENERAL ], key = "'code-' + #code", unless = "#result == null")
    fun findByCode(code: String): Country? = countryRepository.findByAlpha2Code(code)

    fun findByName(pageNumber: Int, pageSize: Int, name: String): Page<Country> = countryRepository.findByNameStartingWithIgnoreCase(PageRequest.of(pageNumber, pageSize), name)
}