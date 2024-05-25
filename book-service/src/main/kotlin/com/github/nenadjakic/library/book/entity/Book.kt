package com.github.nenadjakic.library.book.entity

import jakarta.persistence.*
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import java.util.*

@Entity
@Table(name = "book")
@SoftDelete(columnName = "deleted", strategy = SoftDeleteType.DELETED)
class Book : Auditable<String>() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(name = "isbn", length = 1000, nullable = false, unique = true)
    lateinit var isbn: String

    @Column(name = "title", length = 1000, nullable = false)
    lateinit var title: String

    @Column(name = "edition")
    var edition: Int? = null

    @Column(name = "page_count")
    var pageCount: Int? = null
}