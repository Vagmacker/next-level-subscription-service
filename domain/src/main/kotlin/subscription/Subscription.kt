package com.nextlevel.subscription.domain.subscription

import java.time.Instant
import java.time.LocalDate
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.account.AccountId

class Subscription private constructor(
    id: SubscriptionId,
    val version: Int,
    planId: PlanId,
    accountId: AccountId,
    dueDate: LocalDate,
    status: SubscriptionStatus,
    lastRenewDate: Instant?,
    lastTransactionId: String?,
    createdAt: Instant,
    updatedAt: Instant
) : AggregateRoot<SubscriptionId>(id) {

    var planId: PlanId = planId
        private set

    var accountId: AccountId = accountId
        private set

    var dueDate: LocalDate = dueDate
        private set

    var status: SubscriptionStatus = status
        private set

    var lastRenewDate: Instant? = lastRenewDate
        private set

    var lastTransactionId: String? = lastTransactionId
        private set

    var createdAt: Instant = createdAt
        private set

    var updatedAt: Instant = updatedAt
        private set

    val isCanceled: Boolean = status === SubscriptionStatus.CANCELED

    val isActive: Boolean = status === SubscriptionStatus.ACTIVE

    val isTrail: Boolean = status === SubscriptionStatus.TRAILING

    val isIncomplete: Boolean = status === SubscriptionStatus.INCOMPLETE

    companion object {
        const val INIT_VERSION = 0
        fun newSubscription(
            anId: SubscriptionId,
            aSelectPlan: PlanId,
            anAccountId: AccountId
        ): Subscription {
            val now = Instant.now()
            return Subscription(
                anId,
                INIT_VERSION,
                aSelectPlan,
                anAccountId,
                LocalDate.now().plusMonths(1),
                SubscriptionStatus.TRAILING,
                null,
                null,
                now,
                now
            )
        }

        fun with(
            id: SubscriptionId,
            version: Int,
            planId: PlanId,
            accountId: AccountId,
            dueDate: LocalDate,
            status: String,
            lastRenewDate: Instant?,
            lastTransactionId: String?,
            createdAt: Instant,
            updatedAt: Instant
        ): Subscription {
            return Subscription(
                id,
                version,
                planId,
                accountId,
                dueDate,
                SubscriptionStatus.valueOf(status),
                lastRenewDate,
                lastTransactionId,
                createdAt,
                updatedAt
            )
        }
    }

    fun execute(command: SubscriptionCommand) {
        when (command) {
            is SubscriptionCommand.ChangeStatus -> apply { status = command.status }
        }

        updatedAt = Instant.now()
    }
}