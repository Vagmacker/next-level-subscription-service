package com.nextlevel.subscription.domain

interface Identifier<T> {
    fun value(): T
}