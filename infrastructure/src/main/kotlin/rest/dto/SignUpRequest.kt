package com.nextlevel.subscription.infrastructure.rest.dto

import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.application.account.CreateIdpUser
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:[NotBlank Size(max = 255)] override val firstname: String,
    @field:[NotBlank Size(max = 255)] override val lastname: String,
    @field:[NotBlank Size(max = 255) Email] override val email: String,
    @field:[NotBlank Size(max = 28)] override val password: String,
    @field:[NotBlank Size(min = 11, max = 14)] override val documentNumber: String,
    override val documentType: String,
    override val userId: String = "",
    override val accountId: String = "",
) : CreateAccount.Input, CreateIdpUser.Input {
    
}