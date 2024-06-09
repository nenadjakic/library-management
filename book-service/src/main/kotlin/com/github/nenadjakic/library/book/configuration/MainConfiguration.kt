package com.github.nenadjakic.library.book.configuration

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.nenadjakic.library.book.client.CountryClient
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.scheduling.annotation.EnableAsync
import java.time.Duration

const val PUBLISHER_CACHE_COUNTRY_GENERAL = "publisher-cache-country-general"
const val PUBLISHER_CACHE_PUBLISHER_GENERAL = "publisher-cache-publisher-general"
const val PUBLISHER_CACHE_PUBLISHER_PAGEABLE = "publisher-cache-publisher-pageable"

@Configuration
@EnableJpaAuditing
@EnableFeignClients(clients = [ CountryClient::class ])
@EnableAsync
@EnableCaching
open class MainConfiguration(
    private val objectMapper: ObjectMapper
) {

    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        return modelMapper
    }

    @Bean
    fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer {
        val mapper = objectMapper.copy()
        mapper
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .activateDefaultTyping(
                mapper.polymorphicTypeValidator,
                ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY
            )

        return RedisCacheManagerBuilderCustomizer {
            it
                .withCacheConfiguration(
                    PUBLISHER_CACHE_COUNTRY_GENERAL,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(6))
                        .disableCachingNullValues()
                        .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(mapper)))
                )
                .withCacheConfiguration(
                    PUBLISHER_CACHE_PUBLISHER_GENERAL,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(6))
                        .disableCachingNullValues()
                        .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(mapper)))
                )
                .withCacheConfiguration(
                    PUBLISHER_CACHE_PUBLISHER_PAGEABLE,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(6))
                        .disableCachingNullValues()
                        .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.java()))
                )
        }
    }
}