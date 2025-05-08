package com.nextlevel.subscription.domain.person

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.nextlevel.subscription.domain.exceptions.DomainException

class DocumentTest {

    @Test
    fun `given a valid cpf when instantiate then should return it`() {
        // Given
        val expectedType = "cpf"
        val expectedDocument = "12345678901"

        // When
        val actualDocument = Document.create(expectedDocument, expectedType)

        // Then
        assert(actualDocument is Document.Cpf)
        assert(actualDocument.type == expectedType)
        assert(actualDocument.value == expectedDocument)
    }

    @Test
    fun `given an invalid cpf length when instantiate then should return error`() {
        // Given
        val expectedType = "cpf"
        val expectedDocument = "123"
        val expectedErrorMessage = "cpf is invalid"

        // When
        val actualException = assertThrows<DomainException> {
            Document.create(expectedDocument, expectedType)
        }

        // Then
        assertEquals(expectedErrorMessage, actualException.message)
    }

    @Test
    fun `given an empty cpf when instantiate then should return error`() {
        // Given
        val expectedType = "cpf"
        val expectedDocument = ""
        val expectedErrorMessage = "cpf must not be empty"

        // When
        val actualException = assertThrows<DomainException> {
            Document.create(expectedDocument, expectedType)
        }

        // Then
        assertEquals(expectedErrorMessage, actualException.message)
    }

    @Test
    fun `given a valid cnpj when instantiate then should return it`() {
        // Given
        val expectedType = "cnpj"
        val expectedDocument = "12345678000195"

        // When
        val actualDocument = Document.create(expectedDocument, expectedType)

        // Then
        assert(actualDocument is Document.Cnpj)
        assert(actualDocument.type == expectedType)
        assert(actualDocument.value == expectedDocument)
    }

    @Test
    fun `given an invalid cnpj length when instantiate then should return error`() {
        // Given
        val expectedType = "cnpj"
        val expectedDocument = "123"
        val expectedErrorMessage = "cnpj is invalid"

        // When
        val actualException = assertThrows<DomainException> {
            Document.create(expectedDocument, expectedType)
        }

        // Then
        assertEquals(expectedErrorMessage, actualException.message)
    }

    @Test
    fun `given an empty cnpj when instantiate then should return error`() {
        // Given
        val expectedType = "cnpj"
        val expectedDocument = ""
        val expectedErrorMessage = "cnpj must not be empty"

        // When
        val actualException = assertThrows<DomainException> {
            Document.create(expectedDocument, expectedType)
        }

        // Then
        assertEquals(expectedErrorMessage, actualException.message)
    }
}