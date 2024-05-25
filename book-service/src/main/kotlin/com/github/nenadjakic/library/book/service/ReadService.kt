package com.github.nenadjakic.library.book.service

import org.springframework.data.domain.Page

interface ReadService<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun findPage(pageNumber: Int, pageSize: Int): Page<T>
}