package com.nextlevel.subscription.domain.account

interface AccountGateway {
    fun nextId(): AccountId
    fun allAccounts(): List<Account>
    fun save(account: Account): Account
    fun accountOfId(id: AccountId): Account?
}