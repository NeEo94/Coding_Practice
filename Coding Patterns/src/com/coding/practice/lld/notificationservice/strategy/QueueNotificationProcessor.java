package com.coding.practice.lld.notificationservice.strategy;

import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.model.Notification;

public class QueueNotificationProcessor implements NotificationProcessorInterface{
    @Override
    public String processNotification(Notification notification) {
        // add to queue later TODO
        return null;
    }
}
