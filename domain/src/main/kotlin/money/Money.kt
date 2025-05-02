package com.nextlevel.subscription.domain.money

import java.util.Currency

data class Money(
    val amount: Double,
    val currency: Currency
) {
    init {
        require(amount >= 0) { "amount must be non-negative" }
        require(currency != null) { "currency must not be null" }
    }

    constructor(amount: Double, currencyCode: String) : this(amount, Currency.getInstance(currencyCode))
}
