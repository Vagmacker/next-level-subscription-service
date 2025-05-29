package com.nextlevel.subscription.application.subscription.impl

import com.nextlevel.subscription.application.subscription.CreateSubscription
import com.nextlevel.subscription.domain.account.Account
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.exceptions.DomainException
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.subscription.Subscription
import com.nextlevel.subscription.domain.subscription.SubscriptionGateway

class DefaultCreateSubscription(
    private val planGateway: PlanGateway,
    private val accountGateway: AccountGateway,
    private val subscriptionGateway: SubscriptionGateway,
) : CreateSubscription() {
    override fun execute(input: Input): Output {
        val planId = PlanId(input.planId)
        val accountId = AccountId(input.accountId)

        val aPlan = planGateway.planOfId(planId) ?: throw DomainException.notFound(Plan::class, planId)
        val anAccountUser =
            accountGateway.accountOfId(accountId) ?: throw DomainException.notFound(Account::class, accountId)

        return subscriptionGateway.nextId()
            .let { id ->
                subscriptionGateway.save(
                    Subscription.newSubscription(
                        anId = id,
                        aSelectPlan = aPlan.id,
                        anAccountId = anAccountUser.id
                    )
                )
            }
            .let { subscription -> Output(subscription.id.value) }
    }
}