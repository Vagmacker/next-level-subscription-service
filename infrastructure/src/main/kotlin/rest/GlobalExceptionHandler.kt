package com.nextlevel.subscription.infrastructure.rest

import com.nextlevel.subscription.domain.exceptions.DomainException
import com.nextlevel.subscription.domain.validation.handler.Notification
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import com.nextlevel.subscription.domain.validation.Error as ValidationError

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<in Any>? = ResponseEntity.unprocessableEntity()
        .body(Notification.create(convertError(ex.getBindingResult().getAllErrors())))


    @ExceptionHandler(DomainException::class)
    fun handleDomainException(ex: DomainException): ResponseEntity<in Any>? =
        ResponseEntity.unprocessableEntity().body(ex.errors)

    private fun convertError(errors: List<ObjectError>): MutableList<ValidationError> =
        errors.map { e -> ValidationError(e.objectName, e.defaultMessage) }
            .toMutableList()
}