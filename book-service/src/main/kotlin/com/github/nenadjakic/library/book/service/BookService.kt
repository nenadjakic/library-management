package com.github.nenadjakic.library.book.service

import com.github.nenadjakic.library.book.entity.Book
import com.github.nenadjakic.library.book.repository.BookRepository
import com.github.nenadjakic.library.shared.CrudService
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
open class BookService(
    private val bookRepository: BookRepository
) : CrudService<Book, UUID> {

    override fun findAll(): List<Book> = bookRepository.findAll()

    override fun findPage(pageNumber: Int, pageSize: Int): Page<Book> = bookRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id")))

    override fun findById(id: UUID): Book? = bookRepository.findById(id).orElse(null)

    override fun insert(entity: Book): Book = bookRepository.save(entity)

    override fun update(entity: Book): Book {
        val exists = bookRepository.existsById(entity.id!!)
        if (!exists) {
            throw EntityNotFoundException("Book with given id does not exist.")
        }
        return bookRepository.save(entity)
    }

    override fun delete(entity: Book) = bookRepository.delete(entity)

    override fun deleteById(id: UUID) = bookRepository.deleteById(id)
}