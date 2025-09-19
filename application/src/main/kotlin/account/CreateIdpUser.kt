package com.nextlevel.subscription.application.account

import com.nextlevel.subscription.application.UseCase
import com.nextlevel.subscription.domain.account.idp.UserId

abstract class CreateIdpUser: UseCase<CreateIdpUser.Input, CreateIdpUser.Output>() {
    interface Input {
        val accountId: String
        val firstname: String
        val lastname: String
        val email: String
        val password: String
    }

    interface Output {
        val idpUserId: UserId
    }
}