package com.nextlevel.subscription.application.subscription

import com.nextlevel.subscription.application.UseCase

abstract class CreateSubscription : UseCase<CreateSubscription.Input, CreateSubscription.Output>() {

    data class Input(
        val accountId: String,
        val planId: Long,
    )

    data class Output(
        val subscriptionId: String
    )
}