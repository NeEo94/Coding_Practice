package com.coding.practice.lld.notificationservice.channels;

import com.coding.practice.lld.notificationservice.data.NotificationDbHandler;
import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.api.model.NotificationRequest;
import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.model.Notification;

public class SMSHandler implements ChannelHandler{
    NotificationDbHandler notificationDbHandler = NotificationDbHandler.getDbHandler();
    @Override
    public String processNotification(Notification notificationRequest) {
        // 3rd party email driver
        // save to db
        notificationDbHandler.updateNotification(notificationRequest.getNotificationId(), NOTIFICATIONSTATUS.DELIVERED);
        return NOTIFICATIONSTATUS.DELIVERED.toString();
    }
}
