package com.example.springbootwebsocketangularv002.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WsChatController {


    @MessageMapping("chat.sendMessage")  // Maps messages sent to "chat.sendMessage" WebSocket destination
    @SendTo("/topic/public")  // Specifies that the return message will be sent to "/topic/public"
    public WsChatMessage sendMessage(@Payload WsChatMessage msg) {
        // Log the sender and content of the message for debugging
        System.out.println("Message received from " + msg.getSender() + ": " + msg.getContent());
        
        // Broadcast the message to all subscribers on the "/topic/public" topic
        return msg;
    }

    @MessageMapping("chat.addUser")  // Maps messages sent to "chat.addUser" WebSocket destination
    @SendTo("/topic/chat")  // Specifies that the return message will be sent to "/topic/chat"
    public WsChatMessage addUser(@Payload WsChatMessage msg, SimpMessageHeaderAccessor headerAccessor) {
        // Store the username in the WebSocket session attributes
        headerAccessor.getSessionAttributes().put("username", msg.getSender());
        
        // Log when a user joins the chat
        System.out.println("User joined: " + msg.getSender());
        
        // Broadcast the user join event to all subscribers on the "/topic/chat" topic
        return msg;
    }

}
