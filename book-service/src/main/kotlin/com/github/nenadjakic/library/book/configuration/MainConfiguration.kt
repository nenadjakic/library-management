package com.github.nenadjakic.library.book.configuration

import com.github.nenadjakic.library.book.client.CountryClient
import org.modelmapper.ModelMapper
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
@EnableFeignClients(clients = [ CountryClient::class ])
open class MainConfiguration {

    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        return modelMapper
    }
}