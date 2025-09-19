package com.nextlevel.subscription.infrastructure.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

enum class Json {
    INSTANCE;

    private val mapper: ObjectMapper = Jackson2ObjectMapperBuilder()
        .dateFormat(StdDateFormat())
        .featuresToDisable(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
            DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES,
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
        )
        .modules(JavaTimeModule())
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .build()

    companion object {
        @JvmStatic
        fun mapper(): ObjectMapper {
            return INSTANCE.mapper.copy()
        }

        @JvmStatic
        fun writeValueAsBytes(obj: Any): ByteArray {
            return invoke { INSTANCE.mapper.writeValueAsBytes(obj) }
        }

        @JvmStatic
        fun writeValueAsString(obj: Any): String {
            return invoke { INSTANCE.mapper.writeValueAsString(obj) }
        }

        @JvmStatic
        fun <T> readValue(json: ByteArray, clazz: Class<T>): T {
            return invoke { INSTANCE.mapper.readValue(json, clazz) }
        }

        @JvmStatic
        fun <T> readValue(json: String, clazz: Class<T>): T {
            return invoke { INSTANCE.mapper.readValue(json, clazz) }
        }

        @JvmStatic
        fun <T> readValue(json: String, clazz: TypeReference<T>): T {
            return invoke { INSTANCE.mapper.readValue(json, clazz) }
        }

        private fun <T> invoke(callable: () -> T): T {
            return try {
                callable()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}