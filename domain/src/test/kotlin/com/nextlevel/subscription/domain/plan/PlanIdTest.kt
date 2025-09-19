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
        assertEquals(expectedId, actualPlanId.value)
    }

    @Test
    fun `given an empty value when calls empty then should return ok`() {
        // Given
        val expectedId: Long? = null

        // When
        val actualPlanId = PlanId.empty()

        // Then
        assertEquals(expectedId, actualPlanId.value)
    }
}