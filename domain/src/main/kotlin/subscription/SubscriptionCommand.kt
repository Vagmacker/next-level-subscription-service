package com.nextlevel.subscription.domain.subscription

sealed interface SubscriptionCommand {
    class ChangeStatus(val status: SubscriptionStatus) : SubscriptionCommand
}