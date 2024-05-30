package com.github.nenadjakic.library.shared

interface CrudController<CR, UR, RE, ID> : WriteController<CR, UR, ID>, ReadController<RE, ID>