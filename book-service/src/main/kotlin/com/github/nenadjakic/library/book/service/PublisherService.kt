package com.github.nenadjakic.library.book.service

import com.github.nenadjakic.library.book.configuration.PUBLISHER_CACHE_PUBLISHER_GENERAL
import com.github.nenadjakic.library.book.configuration.PUBLISHER_CACHE_PUBLISHER_PAGEABLE
import com.github.nenadjakic.library.book.entity.Publisher
import com.github.nenadjakic.library.book.repository.PublisherRepository
import com.github.nenadjakic.library.shared.CrudService
import jakarta.persistence.EntityNotFoundException
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class PublisherService(
    private val publisherRepository: PublisherRepository
) : CrudService<Publisher, UUID> {

    @CachePut(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_GENERAL ], key = "#result.id", unless = "#result == null")
    @CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_PAGEABLE ], allEntries = true )
    override fun insert(entity: Publisher): Publisher {
        entity.id = UUID.randomUUID()
        return publisherRepository.save(entity)
    }

    @CachePut(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_GENERAL ], key = "#entity.id", unless = "#result == null")
    @CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_PAGEABLE ], allEntries = true )
    override fun update(entity: Publisher): Publisher {
        val exists = publisherRepository.existsById(entity.id!!)
        if (!exists) {
            throw EntityNotFoundException("Publisher with given id does not exist.")
        }
        return publisherRepository.save(entity)
    }

    @Caching(evict = [
        CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_GENERAL ], key = "#entity.id"),
        CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_PAGEABLE ], allEntries = true )
    ])
    override fun delete(entity: Publisher) = publisherRepository.delete(entity)

    @Caching(evict = [
        CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_GENERAL ], key = "#entity.id"),
        CacheEvict(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_PAGEABLE ], allEntries = true )
    ])
    override fun deleteById(id: UUID) = publisherRepository.deleteById(id)

    override fun findAll(): List<Publisher> = publisherRepository.findAll()

    @Cacheable(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_PAGEABLE ], key = "#pageNumber + '-' + #pageSize")
    override fun findPage(pageNumber: Int, pageSize: Int): Page<Publisher> = publisherRepository.findAll(PageRequest.of(pageNumber, pageSize))

    @Cacheable(cacheNames = [ PUBLISHER_CACHE_PUBLISHER_GENERAL ], key = "#id", unless="#result == null")
    override fun findById(id: UUID): Publisher? = publisherRepository.findById(id).orElse(null)
}