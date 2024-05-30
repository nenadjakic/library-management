package com.github.nenadjakic.library.book.repository

import com.github.nenadjakic.library.book.entity.Publisher
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PublisherRepository : JpaRepository<Publisher, UUID>