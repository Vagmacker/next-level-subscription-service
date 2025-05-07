package com.nextlevel.subscription.domain

import java.util.Objects

abstract class Entity<ID : Identifier<*>> {
    val id: ID
    val domainEvents: MutableList<DomainEvent>

    protected constructor(id: ID) : this(id, null)

    protected constructor(id: ID, domainEvents: MutableList<DomainEvent>?) {
        requireNotNull(id) { "id must not be null" }
        this.id = id
        this.domainEvents = domainEvents ?: mutableListOf()
    }

    fun registerEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entity<*>

        return id.equals(other.id)
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}
