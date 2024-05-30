package com.github.nenadjakic.library.book.service

import com.github.nenadjakic.library.book.entity.Publisher
import com.github.nenadjakic.library.book.repository.PublisherRepository
import com.github.nenadjakic.library.shared.CrudService
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PublisherService(
    private val publisherRepository: PublisherRepository
) : CrudService<Publisher, UUID> {
    override fun insert(entity: Publisher): Publisher = publisherRepository.save(entity)

    override fun update(entity: Publisher): Publisher {
        val exists = publisherRepository.existsById(entity.id!!)
        if (!exists) {
            throw EntityNotFoundException("Publisher with given id does not exist.")
        }
        return publisherRepository.save(entity)
    }

    override fun delete(entity: Publisher) = publisherRepository.delete(entity)

    override fun deleteById(id: UUID) = publisherRepository.deleteById(id)

    override fun findAll(): List<Publisher> = publisherRepository.findAll()

    override fun findPage(pageNumber: Int, pageSize: Int): Page<Publisher> = publisherRepository.findAll(PageRequest.of(pageNumber, pageSize))

    override fun findById(id: UUID): Publisher? = publisherRepository.findById(id).orElse(null)
}