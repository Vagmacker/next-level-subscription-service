package com.nextlevel.subscription.infrastructure.rest.dto

import jakarta.validation.constraints.NotNull

data class CreateSubscriptionRequest(@field:NotNull val planId: Long)
