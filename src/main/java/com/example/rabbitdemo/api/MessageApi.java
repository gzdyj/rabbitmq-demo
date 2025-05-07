package com.example.rabbitdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rabbitdemo.producer.MessageProducer;
import com.example.rabbitdemo.common.ApiResponse;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Component
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageApi {

    @Autowired
    private MessageProducer messageProducer;

    @POST
    @Path("/send")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response sendMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(ApiResponse.error("message不能为空"))
                .build();
        }
        messageProducer.sendMessage(message);
        return Response.ok(ApiResponse.ok("ok")).build();
    }

    @GET
    public Response getMessage(@QueryParam("message") String message) {
        if (message == null || message.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(ApiResponse.error("message不能为空"))
                .build();
        }
        messageProducer.sendMessage(message);
        return Response.ok(ApiResponse.ok("Message sent: " + message)).build();
    }
} 