package com.nextlevel.subscription.infrastructure.rest.controllers

import com.nextlevel.subscription.infrastructure.mediator.SignUpMediator
import com.nextlevel.subscription.infrastructure.rest.AccountRestApi
import com.nextlevel.subscription.infrastructure.rest.dto.SignUpRequest
import com.nextlevel.subscription.infrastructure.rest.dto.SignUpResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class AccountRestController(private val signUpMediator: SignUpMediator) : AccountRestApi {

    override fun signUp(req: SignUpRequest): ResponseEntity<SignUpResponse> {
        return signUpMediator.signUp(req)
            .let { ResponseEntity.created(URI.create("/accounts/${it.accountId}")).body(it) }
    }
}