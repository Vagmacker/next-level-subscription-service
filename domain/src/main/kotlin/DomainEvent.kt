package com.nextlevel.subscription.domain

import java.time.Instant

interface DomainEvent {
    val aggregateId: String
    val eventType: String
    val occurredOn: Instant
}