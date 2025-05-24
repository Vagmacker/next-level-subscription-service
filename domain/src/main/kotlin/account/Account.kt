package com.nextlevel.subscription.domain.account

import com.nextlevel.subscription.domain.person.Name
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.person.Address
import com.nextlevel.subscription.domain.person.Document

class Account private constructor(
    id: AccountId,
    name: Name,
    document: Document,
    email: Email,
    billingAddress: Address
) : AggregateRoot<AccountId>(id) {

    var name: Name = name
        private set

    var document: Document = document
        private set

    var email: Email = email
        private set

    var billingAddress: Address = billingAddress
        private set

    companion object {
        fun newAccount(
            id: AccountId,
            name: Name,
            document: Document,
            email: Email,
            billingAddress: Address
        ): Account = Account(id, name, document, email, billingAddress)
    }
}