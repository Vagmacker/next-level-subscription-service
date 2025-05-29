package com.nextlevel.subscription.application.account

import com.nextlevel.subscription.application.UseCase
import com.nextlevel.subscription.domain.account.Account
import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.account.idp.UserId
import com.nextlevel.subscription.domain.person.Document
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.person.Name

abstract class CreateAccount : UseCase<CreateAccount.Input, CreateAccount.Output>() {

    data class Input(
        val id: String,
        val userId: String,
        val firstname: String,
        val lastname: String,
        val documentNumber: String,
        val documentType: String,
        val email: String,
    )

    data class Output(
        val accountId: String
    )

    fun Input.toDomain(): Account = Account.newAccount(
        id = AccountId(id),
        userId = UserId(userId),
        name = Name(firstname, lastname),
        document = Document.create(documentNumber, documentType),
        email = Email(email),
    )
}