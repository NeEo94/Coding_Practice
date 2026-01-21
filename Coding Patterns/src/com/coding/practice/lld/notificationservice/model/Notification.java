package com.coding.practice.lld.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    String notificationId;
    int userId;
    MessageTemplate messageTemplate;
    String message;
    NotificationChannel notificationChannel;
    PRIORITY priority;
    Date created;
    NOTIFICATIONSTATUS status;
}