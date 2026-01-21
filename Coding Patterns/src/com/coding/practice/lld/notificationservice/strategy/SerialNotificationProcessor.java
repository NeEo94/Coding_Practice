package com.coding.practice.lld.notificationservice.strategy;

import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.channels.ChannelHandler;
import com.coding.practice.lld.notificationservice.helper.NotificationHelper;
import com.coding.practice.lld.notificationservice.model.Notification;
import com.coding.practice.lld.notificationservice.model.NotificationChannel;

import java.util.Optional;

public class SerialNotificationProcessor implements NotificationProcessorInterface{
    @Override
    public String processNotification(Notification notification) {
        return Optional.ofNullable(
                NotificationHelper.getChannelHandlerFromFactory(notification.getNotificationChannel()))
                .map(channelHandler -> channelHandler.processNotification(notification))
                .orElse("ERROR");
    }
}
