package com.coding.practice.lld.notificationservice.model;

import lombok.Data;
import lombok.Value;

import java.util.Date;

@Value
public class MessageTemplate {
    int templateId;
    String templateName;
    String templateBody;
    Date updated;
}
