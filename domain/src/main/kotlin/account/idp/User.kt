package com.nextlevel.subscription.domain.account.idp

import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.person.Name

class User private constructor(
    val id: UserId?,
    val accountId: AccountId,
    val name: Name,
    val email: Email,
    val enabled: Boolean,
    val emailVerified: Boolean,
    val password: String,
) {

    companion object {
        fun newUser(
            accountId: AccountId,
            name: Name,
            email: Email,
            password: String,
        ): User = User(null, accountId, name, email, enabled = true, emailVerified = false, password)
    }
}