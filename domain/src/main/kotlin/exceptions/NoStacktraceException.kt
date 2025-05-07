package com.nextlevel.subscription.domain.exceptions

import java.lang.RuntimeException

open class NoStacktraceException : RuntimeException {

    constructor(message: String) : this(message, null)

    constructor(message: String, cause: Throwable?) : super(message, cause, true, false)
}