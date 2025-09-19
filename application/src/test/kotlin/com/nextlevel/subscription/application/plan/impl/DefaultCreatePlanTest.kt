package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.UseCaseTest
import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DefaultCreatePlanTest : UseCaseTest() {

    private val planGateway = mockk<PlanGateway>(relaxed = true)

    @InjectMockKs
    private var useCase = DefaultCreatePlan(planGateway)

    private val captor = slot<Plan>()

    @Test
    fun `given a valid input when calls execute then should create a plan`() {
        // Given
        val expectedVersion = 0
        val expectedPrice = 9.99
        val expectedName = "Plus"
        val expectedActive = true
        val expectedCurrency = "USD"
        val expectedDescription = "Plus plan"
        val expectedPlanId = PlanId(999L)

        every { planGateway.nextId() } returns expectedPlanId
        every { planGateway.save(any()) } answers { firstArg() }

        // When
        val output = useCase.execute(
            CreatePlanInput(
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
            planGateway.nextId()
            planGateway.save(capture(captor))
        }

        val actualPlan = captor.captured

        assertEquals(expectedPlanId, actualPlan.id)
        assertEquals(expectedName, actualPlan.name)
        assertEquals(expectedActive, actualPlan.active)
        assertEquals(expectedVersion, actualPlan.version)
        assertEquals(expectedDescription, actualPlan.description)
        assertEquals(actualPlan.createdAt, actualPlan.updatedAt)
        assertEquals(Money(expectedPrice, expectedCurrency), actualPlan.price)
    }

    data class CreatePlanInput(
        override val name: String,
        override val description: String,
        override val price: Double,
        override val currency: String,
        override val active: Boolean
    ) : CreatePlan.Input
}