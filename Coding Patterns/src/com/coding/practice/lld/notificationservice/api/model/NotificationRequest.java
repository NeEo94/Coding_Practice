package com.coding.practice.lld.notificationservice.api.model;

import com.coding.practice.lld.notificationservice.model.MessageTemplate;
import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.model.NotificationChannel;
import com.coding.practice.lld.notificationservice.model.PRIORITY;
import lombok.*;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    int userId;
    MessageTemplate messageTemplate;
    String message;
    NotificationChannel notificationChannel;
    PRIORITY priority;
    Date created;
}

