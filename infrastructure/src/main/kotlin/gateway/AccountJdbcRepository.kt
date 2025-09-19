package com.nextlevel.subscription.infrastructure.gateway

import com.nextlevel.subscription.domain.account.Account
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.account.idp.UserId
import com.nextlevel.subscription.domain.person.Address
import com.nextlevel.subscription.domain.person.DocumentFactory
import com.nextlevel.subscription.domain.person.Email
import com.nextlevel.subscription.domain.person.Name
import com.nextlevel.subscription.infrastructure.jdbc.DatabaseClient
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet
import java.util.UUID

@Repository
class AccountJdbcRepository(private val databaseClient: DatabaseClient) : AccountGateway {

    override fun nextId(): AccountId = AccountId(UUID.randomUUID().toString().replace("-", ""))

    override fun accountOfId(id: AccountId): Account? {
        val sql = """
            SELECT 
                id, version, idp_user_id, email, firstname, lastname, document_number, document_type, address_zip_code, address_number, address_complement, address_country
            FROM accounts
            WHERE id = :id
        """
        return databaseClient.queryOne(sql, mapOf("id" to id.value), accountMapper())
    }

    override fun accountOfUserId(userId: UserId): Account? {
        val sql = """
            SELECT 
                id, version, idp_user_id, email, firstname, lastname, document_number, document_type, address_zip_code, address_number, address_complement, address_country
            FROM accounts
            WHERE idp_user_id = :userId
        """
        return databaseClient.queryOne(sql, mapOf("userId" to userId.value), accountMapper())
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun save(account: Account): Account {
        return if (account.version == 0) {
            create(account)
        } else {
            update(account)
        }
    }

    private fun create(account: Account): Account {
        val sql = """
            INSERT INTO accounts 
                (id, version, idp_user_id, email, firstname, lastname, document_number, document_type, address_zip_code, address_street, address_state, address_city, address_country)
            VALUES 
                (:id, (:version + 1), :userId, :email, :firstName, :lastName, :documentNumber, :documentType, :addressZipCode, :addressStreet, :addressState, :addressCity, :addressCountry)
        """
        databaseClient.insert(sql, createParams(account))
        return account
    }

    private fun update(account: Account): Account {
        val sql = """
            UPDATE accounts
            SET
                version = :version + 1,
                email = :email,
                firstname = :firstName,
                lastname = :lastName,
                document_number = :documentNumber,
                document_type = :documentType,
                address_zip_code = :addressZipCode,
                address_number = :addressNumber,
                address_street = :addressStreet,
                address_state = :addressState,
                address_city = :addressCity,
                address_country = :addressCountry
            WHERE id = :id AND version = :version
        """
        databaseClient.update(sql, createParams(account))
        return account
    }

    private fun createParams(account: Account): Map<String, Any> {
        val params = mutableMapOf(
            "id" to account.id.value,
            "version" to account.version,
            "userId" to account.userId.value,
            "email" to account.email.value,
            "firstName" to account.name.firstname,
            "lastName" to account.name.lastname,
            "documentNumber" to account.document.number,
            "documentType" to account.document.type
        ) as MutableMap<String, Any>

        params["addressCity"] = account.billingAddress?.city ?: ""
        params["addressState"] = account.billingAddress?.state ?: ""
        params["addressStreet"] = account.billingAddress?.street ?: ""
        params["addressCountry"] = account.billingAddress?.country ?: ""
        params["addressZipCode"] = account.billingAddress?.zipcode ?: ""

        return params
    }

    private fun accountMapper(): (ResultSet) -> Account = { rs ->
        val zipCode = rs.getString("address_zip_code")
        Account.with(
            AccountId(rs.getString("id")),
            rs.getInt("version"),
            UserId(rs.getString("user_id")),
            Name(rs.getString("first_name"), rs.getString("last_name")),
            DocumentFactory.create(rs.getString("document"), rs.getString("document_type")),
            Email(rs.getString("email")),
            if (zipCode != null) {
                Address(
                    zipCode,
                    rs.getString("address_street"),
                    rs.getString("address_city"),
                    rs.getString("address_state"),
                    rs.getString("address_country")
                )
            } else {
                null
            }
        )
    }
}