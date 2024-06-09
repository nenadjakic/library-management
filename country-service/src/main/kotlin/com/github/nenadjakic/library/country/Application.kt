package com.github.nenadjakic.library.country

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.time.Duration


@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
class Application(
    private val objectMapper: ObjectMapper
) {
    @Bean
    open fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()

        return modelMapper
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
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
                    COUNTRY_CACHE_COUNTRY_GENERAL,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(6))
                        .disableCachingNullValues()
                        .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(mapper)))
                )
                .withCacheConfiguration(
                    COUNTRY_CACHE_COUNTRY_PAGEABLE,
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(6))
                        .disableCachingNullValues()
                        .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.java()))
                )
        }
    }
}

const val COUNTRY_CACHE_COUNTRY_GENERAL = "country-cache-country"
const val COUNTRY_CACHE_COUNTRY_PAGEABLE = "country-cache-country-pageable"

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

