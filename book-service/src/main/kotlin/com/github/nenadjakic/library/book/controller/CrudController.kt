package com.github.nenadjakic.library.book.controller

interface CrudController<CR, UR, RE, ID> : WriteController<CR, UR, ID>, ReadController<RE, ID>