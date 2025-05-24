package com.nextlevel.subscription.domain.account

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.nextlevel.subscription.domain.UnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import com.nextlevel.subscription.domain.exceptions.DomainException

class AccountIdTest : UnitTest() {

    @Test
    fun `given a value when instantiate then should return it`() {
        // Given
        val expectedId = "123"

        // When
        val actualAccountId = AccountId(expectedId)

        // Then
        assertNotNull(actualAccountId)
        assertEquals(expectedId, actualAccountId.value)
    }

    @Test
    fun `given an empty id value when instantiate then should return error`() {
        // Given
        val expectedId = " "
        val expectedErrorMessage = "accountId must not be empty"

        // Then
        assertThrows<DomainException>(expectedErrorMessage) { AccountId(expectedId) }
    }
}