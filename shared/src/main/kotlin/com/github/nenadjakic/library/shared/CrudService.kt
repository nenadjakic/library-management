package com.github.nenadjakic.library.shared

interface CrudService<T, ID> : WriteService<T, ID>, ReadService<T, ID>