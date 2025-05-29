package com.nextlevel.subscription.application.subscription.impl

import com.nextlevel.subscription.application.UseCaseTest
import com.nextlevel.subscription.application.subscription.CreateSubscription
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.subscription.Subscription
import com.nextlevel.subscription.domain.subscription.SubscriptionGateway
import com.nextlevel.subscription.domain.subscription.SubscriptionId
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DefaultCreateSubscriptionTest : UseCaseTest() {

    private var captor = slot<Subscription>()

    private var planGateway = mockk<PlanGateway>(relaxed = true)

    private var accountGateway = mockk<AccountGateway>(relaxed = true)

    private var subscriptionGateway = mockk<SubscriptionGateway>(relaxed = true)

    private var useCase = DefaultCreateSubscription(planGateway, accountGateway, subscriptionGateway)

    @Test
    fun `given a valid input when calls execute then should create a subscription `() {
        // Given
        val expectedPlanId = PlanId(1L)
        val expectedAccountId = AccountId("ACC123")
        val expectedSubscriptionId = SubscriptionId("SUB123")

        every { planGateway.planOfId(expectedPlanId) } returns mockk()
        every { accountGateway.accountOfId(expectedAccountId) } returns mockk()
        every { subscriptionGateway.nextId() } returns expectedSubscriptionId
        every { subscriptionGateway.save(any()) } answers { firstArg() }

        // When
        val output = useCase.execute(
            CreateSubscription.Input(
                planId = expectedPlanId.value!!,
                accountId = expectedAccountId.value
            )
        )

        assertEquals(expectedSubscriptionId.value, output.subscriptionId)

        // Then
        verify(exactly = 1) { subscriptionGateway.save(capture(captor)) }

        val actualSubscription = captor.captured

        assertEquals(expectedSubscriptionId, actualSubscription.id.value)
    }
}