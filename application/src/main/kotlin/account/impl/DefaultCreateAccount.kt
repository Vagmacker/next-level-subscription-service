package com.nextlevel.subscription.application.account.impl

import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.account.AccountId

class DefaultCreateAccount(private val accountGateway: AccountGateway) : CreateAccount() {
    override fun execute(input: Input): Output =
        StdOutput(accountGateway.save(input.toDomain()).id)

    data class StdOutput(override val accountId: AccountId) : Output
}