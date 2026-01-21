package com.coding.practice.lld.notificationservice.channels;

import com.coding.practice.lld.notificationservice.api.model.NotificationRequest;
import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.model.Notification;

public interface ChannelHandler {
    public String processNotification(Notification notification);
}
