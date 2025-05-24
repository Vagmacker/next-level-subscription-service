package com.nextlevel.subscription.domain.subscription

import com.nextlevel.subscription.domain.exceptions.DomainException

// State pattern
enum class SubscriptionStatus(
    val trailing: (Subscription) -> Unit,
    val incomplete: (Subscription) -> Unit,
    val active: (Subscription) -> Unit,
    val cancel: (Subscription) -> Unit,
) {
    TRAILING(
        trailing = { },
        incomplete = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(INCOMPLETE)) },
        active = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(ACTIVE)) },
        cancel = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(CANCELED)) }
    ),
    INCOMPLETE(
        trailing = { throw DomainException.with("Subscription with status incomplete can´t transit to trailing") },
        incomplete = { },
        active = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(ACTIVE)) },
        cancel = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(CANCELED)) }
    ),
    ACTIVE(
        trailing = { throw DomainException.with("Subscription with status incomplete can´t transit to trailing") },
        incomplete = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(INCOMPLETE)) },
        active = { },
        cancel = { subscription -> subscription.execute(SubscriptionCommand.ChangeStatus(CANCELED)) }
    ),
    CANCELED(
        trailing = { throw DomainException.with("Subscription with status incomplete can´t transit to trailing") },
        incomplete = { throw DomainException.with("Subscription with status incomplete can´t transit to incomplete") },
        active = { throw DomainException.with("Subscription with status incomplete can´t transit to active") },
        cancel = { }
    );
}