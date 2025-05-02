package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanId
import org.junit.jupiter.api.Assertions.*
import com.nextlevel.subscription.domain.plan.PlanRepository
import org.junit.jupiter.api.Test
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class DefaultCreatePlanTest {

    private val planRepository: PlanRepository = mock()
    private val useCase = DefaultCreatePlan(planRepository)
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

        whenever(planRepository.nextId()).thenReturn(expectedPlanId)
        whenever(planRepository.save(any())).thenAnswer(returnsFirstArg<Plan>())

        val input = CreatePlanTestInput(
            expectedName,
            expectedDescription,
            expectedPrice,
            expectedCurrency,
            expectedActive
        )

        // When
        val output = useCase.execute(input)

        assertEquals(expectedPlanId, output.planId())

        // Then
        verify(planRepository, times(1)).save(captor.capture())

        val plan = captor.firstValue

        assertEquals(expectedName, plan.name)
        assertEquals(expectedPlanId, plan.id())
        assertEquals(expectedActive, plan.active)
        assertEquals(expectedDescription, plan.description)
        assertEquals(Money(expectedPrice, expectedCurrency), plan.price)
    }

    data class CreatePlanTestInput(
        override val name: String,
        override val description: String,
        override val price: Double,
        override val currency: String,
        override val active: Boolean
    ) : CreatePlan.Input
}