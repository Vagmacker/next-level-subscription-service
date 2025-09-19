package com.nextlevel.subscription.domain.plan

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import com.nextlevel.subscription.domain.UnitTest
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.exceptions.DomainException

class PlantTest : UnitTest() {

    @Test
    fun `given a valid params when calls newPlan should instantiate`() {
        // Given
        val expectedVersion = 0
        val expectedName = "Plus"
        val expectedActive = true
        val expectedId = PlanId(1L)
        val expectedDescription = "Plus plan"
        val expectedPrice = Money(100.0, "USD")

        // When
        val actualPlan = Plan.newPlan(
            expectedId,
            expectedName,
            expectedDescription,
            expectedPrice,
            expectedActive
        )

        // Then
        assertNotNull(actualPlan)

        actualPlan.apply {
            assertNull(deletedAt)
            assertNotNull(createdAt)
            assertNotNull(updatedAt)
            assertEquals(expectedId, id)
            assertEquals(expectedName, name)
            assertEquals(expectedPrice, price)
            assertEquals(expectedActive, active)
            assertEquals(expectedVersion, version)
            assertEquals(expectedDescription, description)
        }
    }

    @Test
    fun `given an invalid empty name when calls newPlan then should return error`() {
        // Given
        val expectedErrorMessage = "name must not be empty"

        val expectedName = "  "
        val expectedActive = true
        val expectedId = PlanId(1L)
        val expectedDescription = "Plus plan"
        val expectedPrice = Money(100.0, "USD")

        // When
        val exception = assertThrows<DomainException> {
            Plan.newPlan(
                expectedId,
                expectedName,
                expectedDescription,
                expectedPrice,
                expectedActive
            )
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given a name length more than 100 when calls newPlan then should return error`() {
        // Given
        val expectedErrorMessage = "name must be less than 100 characters"

        val expectedActive = true
        val expectedId = PlanId(1L)
        val expectedDescription = "Plus plan"
        val expectedName = "aaaa".repeat(100)
        val expectedPrice = Money(100.0, "USD")

        // When
        val exception = assertThrows<DomainException> {
            Plan.newPlan(
                expectedId,
                expectedName,
                expectedDescription,
                expectedPrice,
                expectedActive
            )
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }
}