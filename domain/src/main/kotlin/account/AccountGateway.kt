package com.nextlevel.subscription.domain.account

interface AccountGateway {
    suspend fun nextId(): AccountId
    suspend fun allAccounts(): List<Account>
    suspend fun save(account: Account): Account
    suspend fun accountOfId(id: AccountId): Account?
}