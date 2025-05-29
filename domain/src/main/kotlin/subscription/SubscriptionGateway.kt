package com.nextlevel.subscription.domain.subscription

import com.nextlevel.subscription.domain.account.AccountId

interface SubscriptionGateway {
    fun nextId(): SubscriptionId
    fun latestSubscriptionOf(accountId: AccountId): Subscription?
    fun subscriptionOf(id: SubscriptionId): Subscription?
    fun save(subscription: Subscription): Subscription
}