package com.github.nenadjakic.library.country.controller

interface CrudController<CR, UR, RE, ID> : WriteController<CR, UR, ID>, ReadController<RE, ID>