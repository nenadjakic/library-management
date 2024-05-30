package com.github.nenadjakic.library.book.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import java.util.UUID

@Entity
@Table(name = "publisher")
@SoftDelete(columnName = "deleted", strategy = SoftDeleteType.DELETED)
class Publisher : Auditable<String>() {
    @Id
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "name", nullable = false, length = 500)
    lateinit var name: String

    @Column(name = "country_id", nullable = false)
    lateinit var countryId: UUID
}