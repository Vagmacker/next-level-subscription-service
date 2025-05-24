package com.nextlevel.subscription.domain.subscription

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import com.nextlevel.subscription.domain.UnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import com.nextlevel.subscription.domain.exceptions.DomainException
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.account.AccountId
import java.time.Instant
import java.time.LocalDate

class SubscriptionStatusTest : UnitTest() {

    @Test
    fun `given a trailing status when calls active then status should transit to active status`() {
        // Given
        val expectedStatus = SubscriptionStatus.ACTIVE
        val subscription = Subscription.newSubscription(
            SubscriptionId("123"),
            PlanId(456L),
            AccountId("789")
        )

        // When
        SubscriptionStatus.TRAILING.active(subscription)

        // Then
        assertEquals(SubscriptionStatus.ACTIVE, subscription.status)
    }

    @Test
    fun `given a TRAILING status when transit to INCOMPLETE then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.TRAILING)

        // When
        SubscriptionStatus.TRAILING.incomplete(subscription)

        // Then
        assertEquals(SubscriptionStatus.INCOMPLETE, subscription.status)
    }

    @Test
    fun `given a TRAILING subscription when transit to CANCELED then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.TRAILING)

        // When
        SubscriptionStatus.TRAILING.cancel(subscription)

        // Then
        assertEquals(SubscriptionStatus.CANCELED, subscription.status)
    }

    @Test
    fun `given a INCOMPLETE subscription when transit to ACTIVE then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.INCOMPLETE)

        // When
        SubscriptionStatus.INCOMPLETE.active(subscription)

        // Then
        assertEquals(SubscriptionStatus.ACTIVE, subscription.status)
    }

    @Test
    fun `given a INCOMPLETE subscription when transit to CANCELED then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.INCOMPLETE)

        // When
        SubscriptionStatus.INCOMPLETE.cancel(subscription)

        // Then
        assertEquals(SubscriptionStatus.CANCELED, subscription.status)
    }

    @Test
    fun `given a INCOMPLETE subscription when transit to TRAILING then should throw exception`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.INCOMPLETE)
        val expectedErrorMessage = "Subscription with status incomplete can´t transit to trailing"

        // When/Then
        val exception = assertThrows<DomainException> { 
            SubscriptionStatus.INCOMPLETE.trailing(subscription) 
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given a ACTIVE subscription when transit to INCOMPLETE then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.ACTIVE)

        // When
        SubscriptionStatus.ACTIVE.incomplete(subscription)

        // Then
        assertEquals(SubscriptionStatus.INCOMPLETE, subscription.status)
    }

    @Test
    fun `given a ACTIVE subscription when transit to CANCELED then status should be updated`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.ACTIVE)

        // When
        SubscriptionStatus.ACTIVE.cancel(subscription)

        // Then
        assertEquals(SubscriptionStatus.CANCELED, subscription.status)
    }

    @Test
    fun `given a ACTIVE subscription when transit to TRAILING then should throw exception`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.ACTIVE)
        val expectedErrorMessage = "Subscription with status incomplete can´t transit to trailing"

        // When/Then
        val exception = assertThrows<DomainException> { 
            SubscriptionStatus.ACTIVE.trailing(subscription) 
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given a CANCELED subscription when transit to TRAILING then should throw exception`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.CANCELED)
        val expectedErrorMessage = "Subscription with status incomplete can´t transit to trailing"

        // When/Then
        val exception = assertThrows<DomainException> { 
            SubscriptionStatus.CANCELED.trailing(subscription) 
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given a CANCELED subscription when transit to INCOMPLETE then should throw exception`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.CANCELED)
        val expectedErrorMessage = "Subscription with status incomplete can´t transit to incomplete"

        // When/Then
        val exception = assertThrows<DomainException> { 
            SubscriptionStatus.CANCELED.incomplete(subscription) 
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    @Test
    fun `given a CANCELED subscription when transit to ACTIVE then should throw exception`() {
        // Given
        val subscription = createSubscription(SubscriptionStatus.CANCELED)
        val expectedErrorMessage = "Subscription with status incomplete can´t transit to active"

        // When/Then
        val exception = assertThrows<DomainException> { 
            SubscriptionStatus.CANCELED.active(subscription) 
        }

        // Then
        assertEquals(expectedErrorMessage, exception.message)
    }

    private fun createSubscription(status: SubscriptionStatus): Subscription {
        return Subscription.newSubscription(
            SubscriptionId("123"),
            PlanId(456L),
            AccountId("789")
        ).apply {
            // Use reflection to set the status directly since it's private
            val statusField = this.javaClass.getDeclaredField("status")
            statusField.isAccessible = true
            statusField.set(this, status)
        }
    }
}
