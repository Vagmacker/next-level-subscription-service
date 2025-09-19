package com.nextlevel.subscription.infrastructure.gateway

import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.infrastructure.jdbc.DatabaseClient
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class PlanJdbcRepository(private val databaseClient: DatabaseClient) : PlanGateway {

    companion object {
        private const val SELECT_FROM_PLANS =
            "SELECT id, version, name, description, active, currency, amount, created_at, updated_at, deleted_at FROM plans"
    }

    override fun nextId(): PlanId = PlanId.empty()

    override fun allPlans(): List<Plan> = databaseClient.query(SELECT_FROM_PLANS, planMapper())

    override fun planOfId(id: PlanId): Plan? {
        val sql = """ $SELECT_FROM_PLANS WHERE id = :id """
        return databaseClient.queryOne(sql, mapOf("id" to id.value!!), planMapper())
    }

    override fun save(plan: Plan): Plan {
        return if (plan.version == 0) {
            create(plan)
        } else {
            update(plan)
        }
    }

    private fun create(plan: Plan): Plan {
        val sql = """
            INSERT INTO plans (version, name, description, active, currency, amount, created_at, updated_at, deleted_at)
            VALUES ((:version + 1), :name, :description, :active, :currency, :amount, :createdAt, :updatedAt, :deletedAt)
            """
        val id = databaseClient.insert(sql, createParams(plan))
        return plan.withId(PlanId(id.toLong()))
    }

    private fun update(plan: Plan): Plan {
        val sql = """
            UPDATE plans
            SET
                version = (:version + 1),
                name = :name,
                description = :description,
                active = :active,
                currency = :currency,
                amount = :amount,
                updated_at = :updatedAt,
                deleted_at = :deletedAt
            WHERE id = :id AND version = :version
        """
        databaseClient.update(sql, createParams(plan))
        return plan
    }

    private fun createParams(plan: Plan): Map<String, Any> {
        val params = mutableMapOf(
            "version" to plan.version,
            "name" to plan.name,
            "description" to plan.description,
            "active" to plan.active,
            "currency" to plan.price.currency,
            "amount" to plan.price.amount,
            "createdAt" to plan.createdAt,
            "updatedAt" to plan.updatedAt,
            "deletedAt" to plan.deletedAt
        ) as MutableMap<String, Any>

        if (plan.id != PlanId.empty()) {
            params.plus("id" to plan.id.value)
        }

        return params
    }

    private fun planMapper(): (ResultSet) -> Plan = { rs ->
        Plan.with(
            id = PlanId(rs.getLong("id")),
            version = rs.getInt("version"),
            name = rs.getString("name"),
            description = rs.getString("description"),
            price = Money(rs.getDouble("amount"), rs.getString("currency")),
            active = rs.getBoolean("active"),
            createdAt = rs.getTimestamp("created_at").toInstant(),
            updatedAt = rs.getTimestamp("updated_at").toInstant(),
            deletedAt = rs.getTimestamp("deleted_at")?.toInstant()
        )
    }
}