package com.nextlevel.subscription

import com.nextlevel.subscription.infrastructure.configuration.WebServerConfig
import org.junit.jupiter.api.Tag
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Tag("integrationTest")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest(classes = [WebServerConfig::class])
@Import(TestApplication::class)
annotation class IntegrationTest {
}