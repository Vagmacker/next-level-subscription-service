package com.nextlevel.subscription.infrastructure.rest.controllers

import com.nextlevel.subscription.application.subscription.CreateSubscription
import com.nextlevel.subscription.infrastructure.rest.SubscriptionRestApi
import com.nextlevel.subscription.infrastructure.rest.dto.CreateSubscriptionRequest
import com.nextlevel.subscription.infrastructure.rest.dto.CreateSubscriptionResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionRestController(private val createSubscription: CreateSubscription) : SubscriptionRestApi {
    override fun createSubscription(@RequestBody @Valid req: CreateSubscriptionRequest): ResponseEntity<CreateSubscriptionResponse> {
        TODO("Not yet implemented")
    }
}