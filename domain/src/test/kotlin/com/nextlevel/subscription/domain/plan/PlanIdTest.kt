package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.UnitTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import com.nextlevel.subscription.domain.exceptions.DomainException

class PlanIdTest : UnitTest() {

    @Test
    fun `given a value when instantiate then should return it`() {
        // Given
        val expectedId = 123L

        // When
        val actualPlanId = PlanId(expectedId)

        // Then
        assertNotNull(actualPlanId)
        assertEquals(expectedId, actualPlanId.value)
    }

    @Test
    fun `given a null id value when instantiate then should return error`() {
        // Given
        val expectedId: Long? = null
        val expectedErrorMessage = "planId must not be null"

        // Then
        assertThrows<DomainException>(expectedErrorMessage) { PlanId(expectedId) }
    }
}