package com.coding.practice.lld.notificationservice.model;

import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.util.List;

@Value
public class UserPref {
    User user;
    List<NotificationChannel> notficationChannelsSubscribed;
}
