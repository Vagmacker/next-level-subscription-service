package com.nextlevel.subscription.infrastructure.rest.controllers

import com.nextlevel.subscription.application.plan.ChangePlan
import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.infrastructure.rest.PlanRestApi
import com.nextlevel.subscription.infrastructure.rest.dto.ChangePlanRequest
import com.nextlevel.subscription.infrastructure.rest.dto.CreatePlanRequest
import com.nextlevel.subscription.infrastructure.rest.dto.CreatePlanResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class PlanRestController(
    private val createPlan: CreatePlan,
    private val changePlan: ChangePlan
) : PlanRestApi {

    override fun createPlan(request: CreatePlanRequest): ResponseEntity<CreatePlanResponse> {
        return CreatePlanResponse(id = createPlan.execute(request).planId.value as Long).let {
            ResponseEntity.created(URI.create("/plans" + it.id)).body(it)
        }
    }

    override fun changePlan(planId: Int, request: ChangePlanRequest): ResponseEntity<Unit> =
        changePlan.execute(request).let { ResponseEntity.noContent().build() }
}