package com.nextlevel.subscription.infrastructure.rest

import com.nextlevel.subscription.infrastructure.rest.dto.SignUpRequest
import com.nextlevel.subscription.infrastructure.rest.dto.SignUpResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["accounts"])
@Tag(name = "Account")
interface AccountRestApi {

    @PostMapping(
        "sign-up",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Operation(summary = "Create a new account")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Account created successfully"),
            ApiResponse(responseCode = "422", description = "A validation error occurred"),
            ApiResponse(responseCode = "500", description = "An unpredictable error occurred")
        ]
    )
    fun signUp(@RequestBody @Valid req: SignUpRequest): ResponseEntity<SignUpResponse>
}