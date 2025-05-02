package com.nextlevel.subscription.domain

abstract class AggregateRoot<ID : Identifier<*>> : Entity<ID> {
    protected constructor(id: ID) : super(id)
    protected constructor(id: ID, domainEvents: MutableList<DomainEvent>) : super(id, domainEvents)
}