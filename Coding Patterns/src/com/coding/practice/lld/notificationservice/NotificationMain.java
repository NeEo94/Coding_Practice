package com.coding.practice.lld.notificationservice;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;
import java.util.concurrent.Executors;

public class NotificationMain {
    public static void main(String[] args) {
        String baseUri = "http://localhost:8080/";

        // Configure Jersey to scan your package for @Path classes
        ResourceConfig config = new ResourceConfig()
                .packages("com.coding.practice.lld.notificationservice.api")
                .register(org.glassfish.jersey.jackson.JacksonFeature.class);

        // Create and start the server
        var server = JdkHttpServerFactory.createHttpServer(URI.create(baseUri), config, false);

        // PRODUCTION: Use Virtual Threads (Available in Java 21+)
        //server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

        server.start();
        System.out.println("--- Notification Service Production Ready ---");
        System.out.println("Endpoint: " + baseUri + "notifications/send");
    }
}
