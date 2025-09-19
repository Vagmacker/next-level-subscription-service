package com.nextlevel.subscription.domain.account

import com.nextlevel.subscription.domain.person.Address
import com.nextlevel.subscription.domain.person.Document
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.person.Name

sealed interface AccountCommand {
    data class ChangeEmail(val email: Email) : AccountCommand
    data class ChangeDocument(val document: Document) : AccountCommand
    data class ChangeProfile(val name: Name, val billingAddress: Address) : AccountCommand
}