package com.github.nenadjakic.library.country.service

interface CrudService<T, ID> : WriteService<T, ID>, ReadService<T, ID>