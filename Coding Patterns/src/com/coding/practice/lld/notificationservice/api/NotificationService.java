package com.coding.practice.lld.notificationservice.api;

import com.coding.practice.lld.notificationservice.api.model.NotificationRequest;
import com.coding.practice.lld.notificationservice.api.model.NotificationResponse;
import com.coding.practice.lld.notificationservice.data.NotificationDbHandler;
import com.coding.practice.lld.notificationservice.helper.NotificationHelper;
import com.coding.practice.lld.notificationservice.model.NOTIFICATIONSTATUS;
import com.coding.practice.lld.notificationservice.model.Notification;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Path("/notifications")
public class NotificationService {

    NotificationDbHandler notificationDbHandler = NotificationDbHandler.getDbHandler();

    @Path("/send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendNotification(NotificationRequest notificationRequest, @Context UriInfo uriInfo) {
        String notificationId = UUID.randomUUID().toString();
        URI uri = uriInfo.getBaseUriBuilder()
                .path(NotificationService.class)
                .path(notificationId)
                .build();
        // add to db then queue or process sync based on strategy
        Notification notification = Notification.builder()
                .userId(notificationRequest.getUserId())
                .priority(notificationRequest.getPriority())
                .messageTemplate(notificationRequest.getMessageTemplate())
                .notificationId(notificationId)
                .notificationChannel(notificationRequest.getNotificationChannel())
                .created(new Date())
                .message(notificationRequest.getMessage())
                .status(NOTIFICATIONSTATUS.NEW)
                .build();

        //notificationRequest.toBuilder().notificationId(notificationId).status(NOTIFICATIONSTATUS.NEW).build();
        notificationDbHandler.createNotification(notification);
        NOTIFICATIONSTATUS status = NotificationHelper.processNotification(notification);
        return Response.accepted()
                .location(uri)
                .entity(new NotificationResponse(notificationId, status.toString()))
                .build();
    }

    @Path("/{notificationId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("notificationId") String notificationId) {
        // fetch from db
         Notification notif = notificationDbHandler.getNotificationResponse(notificationId);
        return Response
                .ok(new NotificationResponse(notificationId, notif.getStatus().toString()))
                .build();
    }

}
