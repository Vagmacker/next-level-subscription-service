package com.nextlevel.subscription.domain.account

import com.nextlevel.subscription.domain.account.idp.UserId

interface AccountGateway {
    fun nextId(): AccountId
    fun save(account: Account): Account
    fun accountOfId(id: AccountId): Account?
    fun accountOfUserId(userId: UserId): Account?
}