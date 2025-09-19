package com.nextlevel.subscription.infrastructure.gateway

import com.nextlevel.subscription.domain.account.AccountId
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.subscription.Subscription
import com.nextlevel.subscription.domain.subscription.SubscriptionGateway
import com.nextlevel.subscription.domain.subscription.SubscriptionId
import com.nextlevel.subscription.infrastructure.jdbc.DatabaseClient
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet
import java.util.*

@Repository
class SubscriptionJdbcRepository(private val databaseClient: DatabaseClient) : SubscriptionGateway {
    override fun nextId(): SubscriptionId = SubscriptionId(UUID.randomUUID().toString().replace("-", ""))

    override fun latestSubscriptionOfAccount(accountId: AccountId): Subscription? {
        val sql = """
            SELECT
                id, version, account_id, plan_id, status, created_at, updated_at, due_date, last_renew_date, last_transaction_id FROM subscriptions
            FROM subscriptions
            WHERE account_id = :accountId
        """
        return databaseClient.queryOne(sql, mapOf("accountId" to accountId), subscriptionMapper())
    }

    override fun subscriptionOfId(id: SubscriptionId): Subscription? {
        val sql = """
            SELECT
                id, version, account_id, plan_id, status, created_at, updated_at, due_date, last_renew_date, last_transaction_id FROM subscriptions
            FROM subscriptions
            WHERE id = :id
        """
        return databaseClient.queryOne(sql, mapOf("id" to id.value), subscriptionMapper());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun save(subscription: Subscription): Subscription {
        if (subscription.version == 0) {
            create(subscription);
        } else {
            update(subscription);
        }

        return subscription
    }

    private fun create(subscription: Subscription) {
        val sql = """
            INSERT INTO subscriptions 
                (id, version, plan_id, account_id, due_date, status, last_renew_date, last_transaction_id, created_at, updated_at)
            VALUES 
                (:id, (:version + 1), :planId, :accountId, :dueDate, :status, :lastRenewDate, :lastTransactionId, :createdAt, :updatedAt)
        """
        databaseClient.insert(sql, createParams(subscription))
    }

    private fun update(subscription: Subscription) {
        val sql = """
            UPDATE subscriptions SET
                version = (:version + 1),
                plan_id = :planId,
                account_id = :accountId,
                due_date = :dueDate,
                status = :status,
                last_renew_date = :lastRenewDate,
                last_transaction_id = :lastTransactionId,
                updated_at = :updatedAt
            WHERE id = :id AND version = :version
        """
        databaseClient.update(sql, createParams(subscription))
    }

    private fun createParams(subscription: Subscription): Map<String, Any> = mapOf(
        "id" to subscription.id.value,
        "version" to subscription.version,
        "planId" to subscription.planId.value!!,
        "accountId" to subscription.accountId.value,
        "dueDate" to subscription.dueDate,
        "status" to subscription.status,
        "lastRenewDate" to subscription.lastRenewDate,
        "lastTransactionId" to subscription.lastTransactionId,
        "createdAt" to subscription.createdAt,
        "updatedAt" to subscription.updatedAt
    ) as Map<String, Any>

    private fun subscriptionMapper(): (ResultSet) -> Subscription = { rs ->
        Subscription.with(
            id = SubscriptionId(rs.getString("id")),
            version = rs.getInt("version"),
            planId = PlanId(rs.getLong("plan_id")),
            accountId = AccountId(rs.getString("account_id")),
            dueDate = rs.getDate("due_date").toLocalDate(),
            status = rs.getString("status"),
            lastRenewDate = rs.getTimestamp("last_renew_date")?.toInstant(),
            lastTransactionId = rs.getString("last_transaction_id"),
            createdAt = rs.getTimestamp("created_at").toInstant(),
            updatedAt = rs.getTimestamp("updated_at").toInstant()
        )
    }
}