package com.nextlevel.subscription.domain

import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanId
import net.datafaker.Faker

class Fixture {

    companion object {
        private val FAKER = Faker()

        object Plans {
            val plus = Plan.newPlan(
                id = PlanId(999L),
                name = "Plus",
                description = FAKER.text().text(100, 500),
                price = Money(9.99, "BRL"),
                active = true
            )
        }
    }
}