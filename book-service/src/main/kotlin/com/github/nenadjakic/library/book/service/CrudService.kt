package com.github.nenadjakic.library.book.service

interface CrudService<T, ID> : WriteService<T, ID>, ReadService<T, ID>