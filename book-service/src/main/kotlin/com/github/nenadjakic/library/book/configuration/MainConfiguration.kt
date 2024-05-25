package com.github.nenadjakic.library.book.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
open class MainConfiguration {

    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        return modelMapper
    }
}