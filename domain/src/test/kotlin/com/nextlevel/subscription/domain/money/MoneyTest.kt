package com.nextlevel.subscription.domain.money

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Currency

class MoneyTest {

    @Test
    fun `should create Money with Currency object`() {
        // Given
        val amount = 100.0
        val currency = Currency.getInstance("USD")
        
        // When
        val money = Money(amount, currency)
        
        // Then
        assertEquals(amount, money.amount)
        assertEquals(currency, money.currency)
    }
    
    @Test
    fun `should create Money with currency code string`() {
        // Given
        val amount = 200.0
        val currencyCode = "EUR"
        val expectedCurrency = Currency.getInstance(currencyCode)
        
        // When
        val money = Money(amount, currencyCode)
        
        // Then
        assertEquals(amount, money.amount)
        assertEquals(expectedCurrency, money.currency)
    }
    
    @Test
    fun `should throw exception when amount is negative`() {
        // Given
        val negativeAmount = -50.0
        val currency = Currency.getInstance("USD")
        
        // When/Then
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Money(negativeAmount, currency)
        }
        assertEquals("amount must be non-negative", exception.message)
    }
    
    @Test
    fun `should throw exception when currency code is invalid`() {
        // Given
        val amount = 100.0
        val invalidCurrencyCode = "INVALID"
        
        // When/Then
        assertThrows(IllegalArgumentException::class.java) {
            Money(amount, invalidCurrencyCode)
        }
    }
}