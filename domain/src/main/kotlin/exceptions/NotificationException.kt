package com.nextlevel.subscription.domain.exceptions

import com.nextlevel.subscription.domain.validation.handler.Notification

class NotificationException(
    message: String,
    notification: Notification,
) : DomainException(message, notification.errors) {

    companion object {
        fun with(message: String, notification: Notification): NotificationException {
            return NotificationException(message, notification)
        }
    }
}