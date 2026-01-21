package com.coding.practice.lld.notificationservice.helper;

import com.coding.practice.lld.notificationservice.channels.ChannelHandler;
import com.coding.practice.lld.notificationservice.channels.EmailHandler;
import com.coding.practice.lld.notificationservice.channels.SMSHandler;
import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.model.Notification;
import com.coding.practice.lld.notificationservice.model.NotificationChannel;
import com.coding.practice.lld.notificationservice.strategy.NotificationProcessorInterface;
import com.coding.practice.lld.notificationservice.strategy.SerialNotificationProcessor;

/*
    This class decides which Processor Interface to use, currently by default we are using serial and not queue,
    we can use queue based on some condition later
 */
public class NotificationHelper {

    public static ChannelHandler getChannelHandlerFromFactory(NotificationChannel notificationChannel) {
        switch(notificationChannel) {
            case EMAIL : return new EmailHandler();
            case SMS : return  new SMSHandler();
            case PUSH: return (id) -> {return "PROCESSING PUSH";};
            default: return  null;
        }
    }

    public static NotificationProcessorInterface getProcessorInterface() {
        return new SerialNotificationProcessor();
    }

    public static NOTIFICATIONSTATUS processNotification(Notification notification) {
        NotificationProcessorInterface notificationProcessorInterface = getProcessorInterface();
        return NOTIFICATIONSTATUS.valueOf(notificationProcessorInterface.processNotification(notification));
    }

}
