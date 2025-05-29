package com.nextlevel.subscription.application.account.impl

import com.nextlevel.subscription.application.UseCaseTest
import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.domain.account.Account
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.account.idp.UserId
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DefaultCreateAccountTest : UseCaseTest() {

    private var accountGateway = mockk<AccountGateway>(relaxed = true)

    private var useCase = DefaultCreateAccount(accountGateway)

    private var captor = slot<Account>()

    @Test
    fun `given a valid input when calls execute then should create an account`() {
        // Given
        val expectedFirstname = "John"
        val expectedLastname = "Doe"
        val expectedEmail = "john@gmail.com"
        val expectedDocumentType = "cpf"
        val expectedDocumentNumber = "12312312323"
        val expectedUserId = UserId("123")
        val expectedAccountId = AccountId("ACC-123")

        every { accountGateway.save(any()) } answers { firstArg() }

        // When
        val output = useCase.execute(
            CreateAccount.Input(
                id = expectedAccountId.value,
                userId = expectedUserId.value,
                firstname = expectedFirstname,
                lastname = expectedLastname,
                email = expectedEmail,
                documentType = expectedDocumentType,
                documentNumber = expectedDocumentNumber
            )
        )

        assertEquals(expectedAccountId.value, output.accountId)

        // Then
        verify(exactly = 1) { accountGateway.save(capture(captor)) }

        val actualAccount = captor.captured

        assertEquals(expectedAccountId, actualAccount.id)
        assertEquals(expectedFirstname, actualAccount.name.firstname)
        assertEquals(expectedLastname, actualAccount.name.lastname)
        assertEquals(expectedEmail, actualAccount.email.value)
        assertEquals(expectedDocumentNumber, actualAccount.document.value)
        assertEquals(expectedUserId, actualAccount.userId)
    }
}