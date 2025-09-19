package com.nextlevel.subscription.infrastructure.rest.dto

import com.nextlevel.subscription.application.plan.ChangePlan
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ChangePlanRequest(
    @field:NotNull override val planId: Long,
    @field:[NotBlank Size(min = 3, max = 100)] override val name: String,
    @field:[NotBlank Size(max = 1000)] override val description: String,
    @field:NotNull override val price: Double,
    @field:[NotBlank Size(min = 3, max = 3)] override val currency: String,
    override val active: Boolean
) : ChangePlan.Input
