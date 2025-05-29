package com.nextlevel.subscription.domain.account.idp

interface IdentityProviderGateway {
    fun create(user: User): UserId

    fun addUserToGroup(userId: UserId, groupId: GroupId)

    fun removeUserFromGroup(userId: UserId, groupId: GroupId)
}