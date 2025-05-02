package com.nextlevel.subscription.application

abstract class UseCase<IN, OUT> {
    abstract fun execute(input: IN): OUT

    fun <T> execute(input: IN, presenter: Presenter<OUT, T>): T {
        if (presenter == null) {
            throw IllegalArgumentException("UseCase 'presenter' is required")
        }
        return presenter.apply(execute(input))
    }
}