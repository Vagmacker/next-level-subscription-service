package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanId
import org.junit.jupiter.api.Assertions.*
import com.nextlevel.subscription.domain.plan.PlanGateway
import org.junit.jupiter.api.Test
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class DefaultCreatePlanTest {

    private val planGateway: PlanGateway = mock()
    private val useCase = DefaultCreatePlan(planGateway)
    private val captor = argumentCaptor<Plan>()

    @Test
    fun `given a valid input when calls execute then should create a plan`() {
        // Given
        val expectedPrice = 9.99
        val expectedName = "Plus"
        val expectedActive = true
        val expectedCurrency = "USD"
        val expectedDescription = "Plus plan"
        val expectedPlanId = PlanId(999L)

        whenever(planGateway.nextId()).thenReturn(expectedPlanId)
        whenever(planGateway.save(any())).thenAnswer(returnsFirstArg<Plan>())

        // When
        val output = useCase.execute(
            CreatePlan.Input(
                name = expectedName,
                description = expectedDescription,
                price = expectedPrice,
                currency = expectedCurrency,
                active = expectedActive
            )
        )

        assertEquals(expectedPlanId, output.planId)

        // Then
        verify(planGateway, times(1)).save(captor.capture())

        captor.firstValue.apply {
            assertEquals(expectedName, name)
            assertEquals(expectedPlanId, id)
            assertEquals(expectedActive, active)
            assertEquals(expectedDescription, description)
            assertEquals(Money(expectedPrice, expectedCurrency), price)
        }
    }
}