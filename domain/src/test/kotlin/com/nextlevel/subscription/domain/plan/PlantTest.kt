package com.nextlevel.subscription.domain.plan

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.nextlevel.subscription.domain.money.Money

class PlantTest {

    @Test
    fun `given a valid params when calls newPlan should instantiate` () {
        // Given
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
        assertNull(actualPlan.deletedAt)
        assertNotNull(actualPlan.createdAt)
        assertNotNull(actualPlan.updatedAt)
        assertEquals(expectedName, actualPlan.name)
        assertEquals(expectedPrice, actualPlan.price)
        assertEquals(expectedActive, actualPlan.active)
        assertEquals(actualPlan.id().value, expectedId.value)
        assertEquals(expectedDescription, actualPlan.description)
        assertInstanceOf(PlanId::class.java, actualPlan.id())
    }
}