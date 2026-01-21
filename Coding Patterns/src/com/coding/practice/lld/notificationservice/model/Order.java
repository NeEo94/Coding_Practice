package com.coding.practice.lld.notificationservice.model;

import lombok.Value;

import java.util.List;

@Value
public class Order {
    List<Item> itemList;
    double totalPrice;
}
