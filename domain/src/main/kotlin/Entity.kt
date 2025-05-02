package com.nextlevel.subscription.domain

abstract class Entity<ID : Identifier<*>> {
    protected val id: ID
    private val domainEvents: MutableList<DomainEvent>

    protected constructor(id: ID) : this(id, null)

    protected constructor(id: ID, domainEvents: MutableList<DomainEvent>?) {
        requireNotNull(id) { "id must not be null" }
        this.id = id
        this.domainEvents = domainEvents ?: mutableListOf()
    }

    fun id(): ID {
        return id
    }

    fun registerEvent(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun domainEvents(): List<DomainEvent> {
        return domainEvents.toList()
    }
}
