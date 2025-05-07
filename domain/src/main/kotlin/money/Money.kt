package com.nextlevel.subscription.domain.money

import java.util.Currency

data class Money(
    val amount: Double,
    val currency: Currency
) {
    constructor(amount: Double, currencyCode: String) : this(amount, Currency.getInstance(currencyCode))
}
