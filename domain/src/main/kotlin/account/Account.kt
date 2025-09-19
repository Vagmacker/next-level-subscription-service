package com.nextlevel.subscription.domain.account

import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.account.idp.UserId
import com.nextlevel.subscription.domain.person.Address
import com.nextlevel.subscription.domain.person.Document
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.person.Name

class Account private constructor(
    id: AccountId,
    var version: Int,
    userId: UserId,
    name: Name,
    document: Document,
    email: Email,
    billingAddress: Address? = null
) : AggregateRoot<AccountId>(id) {

    var userId: UserId = userId
        private set

    var name: Name = name
        private set

    var document: Document = document
        private set

    var email: Email = email
        private set

    var billingAddress: Address? = billingAddress
        private set

    companion object {
        private const val INIT_VERSION = 0

        fun newAccount(
            id: AccountId,
            userId: UserId,
            name: Name,
            document: Document,
            email: Email,
        ): Account = Account(id, INIT_VERSION, userId, name, document, email)

        fun with(
            id: AccountId,
            version: Int,
            userId: UserId,
            name: Name,
            document: Document,
            email: Email,
            billingAddress: Address?
        ): Account = Account(id, version, userId, name, document, email, billingAddress)
    }

    fun execute(command: AccountCommand) {
        when (command) {
            is AccountCommand.ChangeProfile -> {
                this.name = command.name
            }

            is AccountCommand.ChangeDocument -> {
                this.document = command.document
            }

            is AccountCommand.ChangeEmail -> {
                this.email = command.email
            }
        }
        version++
    }
}