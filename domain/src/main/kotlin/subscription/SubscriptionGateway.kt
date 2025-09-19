package com.nextlevel.subscription.domain.subscription

import com.nextlevel.subscription.domain.account.AccountId

interface SubscriptionGateway {
    fun nextId(): SubscriptionId
    fun latestSubscriptionOfAccount(accountId: AccountId): Subscription?
    fun subscriptionOfId(id: SubscriptionId): Subscription?
    fun save(subscription: Subscription): Subscription
}