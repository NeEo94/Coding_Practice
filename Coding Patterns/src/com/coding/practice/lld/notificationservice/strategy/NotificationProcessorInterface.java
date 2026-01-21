package com.coding.practice.lld.notificationservice.strategy;

import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.model.Notification;

public interface NotificationProcessorInterface {
    public String processNotification(Notification notification);
}
