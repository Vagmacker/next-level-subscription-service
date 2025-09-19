package com.nextlevel.subscription.infrastructure.rest

import com.nextlevel.subscription.infrastructure.rest.dto.ChangePlanRequest
import com.nextlevel.subscription.infrastructure.rest.dto.CreatePlanRequest
import com.nextlevel.subscription.infrastructure.rest.dto.CreatePlanResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "Plans", description = "Manage plans")
@RequestMapping("/plans")
interface PlanRestApi {

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(summary = "Create a new plane")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Plan created successfully"),
            ApiResponse(responseCode = "422", description = "A validation error was observed"),
            ApiResponse(responseCode = "500", description = "An unpredictable error was observed")
        ]
    )
    fun createPlan(@RequestBody @Valid request: CreatePlanRequest): ResponseEntity<CreatePlanResponse>

    @PutMapping(
        value = ["{planId}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(summary = "Change a plan information")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Plan changed successfully"),
            ApiResponse(responseCode = "422", description = "A validation error was observed"),
            ApiResponse(responseCode = "500", description = "An unpredictable error was observed")
        ]
    )
    fun changePlan(@PathVariable planId: Int, @RequestBody @Valid request: ChangePlanRequest): ResponseEntity<Unit>
}