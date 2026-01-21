package com.coding.practice.lld.cache;

import java.util.LinkedHashMap;

public class LRUCache {
    static LRUCache lruCache;
    long maxItemsAllowed = 10;
    LinkedHashMap<String, Node> hashMap = new LinkedHashMap<>();

    private LRUCache(){

    }

    public static synchronized LRUCache getCacheInstance() {
        if(lruCache == null)
            lruCache = new LRUCache();
        return lruCache;
    }

    static class Node{
        String value;
        long timeInMillis;

        public Node(String value, long timeInMillis) {
            super();
            this.value = value;
            this.timeInMillis = timeInMillis;
        }
    }

    /*public void addItem(String key, String value) {
        if(hashMap.size() >= maxItemsAllowed) {
            String lastItem = hashMap.lastEntry().getKey();
            hashMap.remove(lastItem);      // to remove last item, which in turn is last used since
        }                                   // we are adding new items or updated items to front while updating timestamp
        long currentTimeMillis = System.currentTimeMillis();
        Node itemNode = hashMap.get(key);
        if(itemNode == null) {
            itemNode = new Node(value, currentTimeMillis);
        } else {
            hashMap.remove(key);
            itemNode.timeInMillis = currentTimeMillis;
        }
        hashMap.putFirst(key, itemNode);
    }

    public String getItem(String key) {
        long currentTimeMillis = System.currentTimeMillis();
        Node itemNode = hashMap.remove(key);
        itemNode.timeInMillis = currentTimeMillis;
        hashMap.putFirst(key, itemNode);
        return itemNode.value;
    }*/

}
