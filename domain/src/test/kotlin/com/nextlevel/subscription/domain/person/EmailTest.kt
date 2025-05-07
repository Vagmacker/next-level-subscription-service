package com.nextlevel.subscription.domain.person

import com.nextlevel.subscription.domain.UnitTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.nextlevel.subscription.domain.exceptions.DomainException

class EmailTest : UnitTest() {

    @Test
    fun `given a valid email address when instantiate then should return it`() {
        // Given
        val expectedEmail = "test@example.com"

        // When
        val email = Email(expectedEmail)

        // Then
        assertEquals(expectedEmail, email.value)
    }

    @Test
    fun `given an empty email address when instantiate then should throw exception`() {
        // Given
        val emptyEmail = ""
        val expectedErrorMessage = "email must not be empty"

        // When/Then
        val exception = assertThrows(DomainException::class.java) {
            Email(emptyEmail)
        }

        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given an invalid email address when instantiate then should throw exception`() {
        // Given
        val expectedEmail = "invalid-email"
        val expectedErrorMessage = "email is invalid"

        // When/Then
        val exception = assertThrows(DomainException::class.java) {
            Email(expectedEmail)
        }

        assertEquals(expectedErrorMessage, exception.message)
    }
}
