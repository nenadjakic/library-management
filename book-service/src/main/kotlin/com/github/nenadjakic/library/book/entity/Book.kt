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
/*
    @ManyToOne
    @JoinColumn(name = "main_author_id", nullable = false)
    lateinit var author: Author

    @OneToMany
    private var _otherAuthors: MutableSet<Author> = mutableSetOf()
    var otherAuthors: Set<Author>
        get() = _otherAuthors.toSet()
        set(value) {
            _otherAuthors.clear()
            _otherAuthors.addAll(value)
        }

 */
}