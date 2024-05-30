package com.github.nenadjakic.library.shared.async

import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

interface ReadController<RE, ID> {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun findAll(): ResponseEntity<List<RE>>

    @GetMapping(value = ["/page"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun findPage(@RequestParam pageNumber: Int, @RequestParam(required = false) pageSize: Int?): ResponseEntity<Page<RE>>

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun findById(@PathVariable id: ID): ResponseEntity<RE>
}