package com.github.nenadjakic.library.country.service

interface WriteService<T, ID> {
    fun insert(entity: T): T
    fun update(entity: T): T
    fun delete(entity: T)
    fun deleteById(id: ID)
}