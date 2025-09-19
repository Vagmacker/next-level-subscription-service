package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.UseCaseTest
import com.nextlevel.subscription.application.plan.ChangePlan
import com.nextlevel.subscription.domain.Fixture
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanGateway
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DefaultChangePlanTest : UseCaseTest() {

    private val planGateway = mockk<PlanGateway>(relaxed = true)

    @InjectMockKs
    private var useCase = DefaultChangePlan(planGateway)

    private val captor = slot<Plan>()

    @Test
    fun `given a valid input when calls execute then should change a plan`() {
        // Given
        val plan = Fixture.Companion.Plans.plus

        val expectedVersion = 1
        val expectedPrice = 660.0
        val expectedName = "Master"
        val expectedActive = true
        val expectedCurrency = "USD"
        val expectedDescription = "Master plan"

        val expectedPlanId = plan.id

        every { planGateway.planOfId(any()) } returns plan
        every { planGateway.save(any()) } answers { firstArg() }

        // When
        val output = useCase.execute(
            ChangePlanInput(
                planId = expectedPlanId.value!!,
                name = expectedName,
                description = expectedDescription,
                price = expectedPrice,
                currency = expectedCurrency,
                active = expectedActive
            )
        )

        assertEquals(expectedPlanId, output.planId)

        // Then
        verifySequence {
            planGateway.planOfId(expectedPlanId)
            planGateway.save(capture(captor))
        }

        val actualPlan = captor.captured

        assertEquals(expectedPlanId, actualPlan.id)
        assertEquals(expectedName, actualPlan.name)
        assertEquals(expectedActive, actualPlan.active)
        assertEquals(expectedVersion, actualPlan.version)
        assertEquals(expectedDescription, actualPlan.description)
        assertTrue(actualPlan.updatedAt.isAfter(actualPlan.createdAt))
        assertEquals(Money(expectedPrice, expectedCurrency), actualPlan.price)
    }

    data class ChangePlanInput(
        override val planId: Long,
        override val name: String,
        override val description: String,
        override val price: Double,
        override val currency: String,
        override val active: Boolean
    ) : ChangePlan.Input
}