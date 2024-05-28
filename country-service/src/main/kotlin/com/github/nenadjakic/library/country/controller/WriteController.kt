package com.github.nenadjakic.library.country.controller

import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@Validated
interface WriteController<CR, UR, ID> {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody model:CR): ResponseEntity<Void>

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@Valid @RequestBody model:UR): ResponseEntity<Void>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<Void>
}