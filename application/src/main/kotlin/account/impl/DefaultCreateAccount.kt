package com.nextlevel.subscription.application.account.impl

import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.domain.account.AccountGateway

class DefaultCreateAccount(private val accountGateway: AccountGateway) : CreateAccount() {
    override fun execute(input: Input): Output =
        Output(accountGateway.save(input.toDomain()).id.value)
}