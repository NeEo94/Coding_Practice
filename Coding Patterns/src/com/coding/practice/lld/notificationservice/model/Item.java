package com.coding.practice.lld.notificationservice.model;

import lombok.Value;

@Value
public class Item {
    int id;
    String itemName;
    double price;
    int qty;
}
