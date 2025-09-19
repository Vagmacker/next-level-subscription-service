package com.nextlevel.subscription.infrastructure.mediator

import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.application.account.CreateIdpUser
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.infrastructure.rest.dto.SignUpRequest
import com.nextlevel.subscription.infrastructure.rest.dto.SignUpResponse
import org.springframework.stereotype.Component

@Component
class SignUpMediator(
    private val accountGateway: AccountGateway,
    private val createAccount: CreateAccount,
    private val createIdpUser: CreateIdpUser
) {

    fun signUp(req: SignUpRequest): SignUpResponse {
        return createAccount(createIdpUser(nexAccountId(req)))
    }

    private fun nexAccountId(req: SignUpRequest): SignUpRequest {
        return req.copy(accountId = accountGateway.nextId().value)
    }

    private fun createIdpUser(req: SignUpRequest): SignUpRequest {
        return createIdpUser.execute(req).let { req.copy(userId = it.idpUserId.value) }
    }

    private fun createAccount(req: SignUpRequest): SignUpResponse {
        return SignUpResponse(createAccount.execute(req).accountId.value)
    }
}