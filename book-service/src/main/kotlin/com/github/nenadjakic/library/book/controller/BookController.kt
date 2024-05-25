package com.github.nenadjakic.library.book.controller

import com.github.nenadjakic.library.book.dto.BookAddRequest
import com.github.nenadjakic.library.book.dto.BookResponse
import com.github.nenadjakic.library.book.dto.BookUpdateRequest
import com.github.nenadjakic.library.book.entity.Book
import com.github.nenadjakic.library.book.service.BookService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
@RequestMapping("/book")
@Validated
open class BookController(
    private val modelMapper: ModelMapper,
    private val bookService: BookService
) : CrudController<BookAddRequest, BookUpdateRequest, BookResponse, UUID> {
    override fun create(model: BookAddRequest): ResponseEntity<Void> {
        val book = modelMapper.map(model, Book::class.java)
        val createdBook = bookService.insert(book)

        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdBook.id)
            .toUri()

        return ResponseEntity.created(location).build()
    }

    override fun update(model: BookUpdateRequest): ResponseEntity<Void> {
        val book = modelMapper.map(model, Book::class.java)
        bookService.update(book)

        return ResponseEntity.noContent().build()
    }

    override fun delete(id: UUID): ResponseEntity<Void> {
        bookService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    override fun findAll(): ResponseEntity<List<BookResponse>> {
        TODO("Not yet implemented")
    }

    override fun findPage(pageNumber: Int, pageSize: Int?): ResponseEntity<Page<BookResponse>> {
        val page = bookService.findPage(pageNumber, pageSize ?: 20);
        val response = page.map { modelMapper.map(it, BookResponse::class.java) }
        return ResponseEntity.ok(response)
    }

    override fun findById(id: UUID): ResponseEntity<BookResponse> {
        val book = bookService.findById(id)
        val response = book?.let { modelMapper.map(book, BookResponse::class.java) }

        return ResponseEntity.ofNullable(response)
    }
}