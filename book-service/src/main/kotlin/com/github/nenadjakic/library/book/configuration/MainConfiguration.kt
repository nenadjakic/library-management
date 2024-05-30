package com.github.nenadjakic.library.book.configuration

import com.github.nenadjakic.library.book.client.CountryClient
import feign.Retryer
import org.modelmapper.ModelMapper
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.TimeUnit

@Configuration
@EnableJpaAuditing
@EnableFeignClients(clients = [ CountryClient::class ])
@EnableAsync
open class MainConfiguration {

    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        return modelMapper
    }
}