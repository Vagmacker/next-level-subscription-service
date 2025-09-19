package com.nextlevel.subscription.application.plan

import com.nextlevel.subscription.IntegrationTest
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.PlanGateway
import io.mockk.verifySequence
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class CreatePlanIntegrationTest {

    @Autowired
    private lateinit var createPlan: CreatePlan

    @Autowired
    private lateinit var planGateway: PlanGateway

    @Test
    fun `given a valid input when calls create plan should return plan id`() {
        // Given
        val expectedName = "Premium Plan"
        val expectedDescription = "Access to all premium features"
        val expectedPrice = 29.99
        val expectedCurrency = "USD"

        val input = CreatePlanInput(
            name = expectedName,
            description = expectedDescription,
            price = expectedPrice,
            currency = expectedCurrency,
            active = true
        )

        // When
        val output = createPlan.execute(input)

        // Then
        assertNotNull(output.planId)

        verifySequence {
            planGateway.nextId()
            planGateway.save(any())
        }

        planGateway.planOfId(output.planId)
            .takeIf { it != null }
            ?.let { plan ->
                assertNotNull(plan.createdAt)
                assertNotNull(plan.updatedAt)
                assertEquals(expectedName, plan.name)
                assertEquals(true, plan.active)
                assertEquals(expectedDescription, plan.description)
                assertEquals(plan.createdAt, plan.updatedAt)
                assertEquals(Money(expectedPrice, expectedCurrency), plan.price)
            }
    }

    data class CreatePlanInput(
        override val name: String,
        override val description: String,
        override val price: Double,
        override val currency: String,
        override val active: Boolean
    ) : CreatePlan.Input
}