package com.github.nenadjakic.library.country.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@JsonIgnoreProperties(value = [ "createdDateTime", "createdBy", "lastModifiedDate", "lastModifiedBy" ])
@Entity
@Table(name = "country")
class Country : Auditable<String>() {

    @Id
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "alpha2_code", nullable = false, unique = true, length = 2)
    lateinit var alpha2Code: String

    @Column(name = "name", nullable = false, unique = true, length = 1000)
    lateinit var name: String
}