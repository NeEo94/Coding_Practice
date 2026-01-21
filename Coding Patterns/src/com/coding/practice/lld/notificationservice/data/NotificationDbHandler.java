package com.coding.practice.lld.notificationservice.data;

import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.api.model.NotificationRequest;
import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.model.Notification;

import java.util.HashMap;

public class NotificationDbHandler {
    static NotificationDbHandler notificationDbHandler;
    private NotificationDbHandler() {
    }
    HashMap<String , Notification> notificationsDB = new HashMap<>();

    public static synchronized NotificationDbHandler getDbHandler(){
        if(notificationDbHandler == null)
            notificationDbHandler = new NotificationDbHandler();
        return notificationDbHandler;
    }

    public void createNotification(Notification notificationRequest) {
        notificationsDB.put(notificationRequest.getNotificationId(), notificationRequest);
    }

    public void updateNotification(String notificationId, NOTIFICATIONSTATUS notificationstatus) {
        Notification notification = notificationsDB.get(notificationId);
        notification.toBuilder().status(notificationstatus).build();
        notificationsDB.put(notificationId, notification);
    }

    public Notification getNotificationResponse (String notificationId) {
        return notificationsDB.get(notificationId);
    }

}
